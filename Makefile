.PHONY: docker-up
docker-up:
	docker compose -f ./.dev/compose.yaml up -d
