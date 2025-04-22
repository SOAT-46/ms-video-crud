package com.fiap.videos.repositories.doubles;

import com.fiap.videos.model.VideoModel;
import com.fiap.videos.repository.VideoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class InMemoryVideoRepository extends DummyVideoRepository {

    private final List<VideoModel> videos;

    public InMemoryVideoRepository(final List<VideoModel> list) {
        super();
        videos = list;
    }

    @Override
    public List<VideoModel> findByUserName(String userName) {
        return videos;
    }

    @Override
    public <S extends VideoModel> S save(S entity) {
        return entity;
    }

    @Override
    public Optional<VideoModel> findById(final Long id) {
        return Optional.of(this.videos.get(0));
    }
}
