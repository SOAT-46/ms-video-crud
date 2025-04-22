package com.fiap.videos.services.doubles;

import com.fiap.videos.services.interfaces.Storage;
import org.springframework.web.multipart.MultipartFile;

public final class DummyStorageService implements Storage {
    @Override
    public String upload(final MultipartFile video) {
        return "";
    }
}
