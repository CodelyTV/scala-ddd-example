CREATE TABLE podcasts
(
    id                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    podcast_id          CHAR(36)            NOT NULL,
    title               VARCHAR(255)        NOT NULL,
    duration_in_seconds BIGINT(20) UNSIGNED NOT NULL,
    description         VARCHAR(255)        NOT NULL,
    rating              INT                 NOT NULL,
    votes               INT                 NOT NULL,
    updated_at          TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    UNIQUE KEY u_podcast_id (podcast_id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;