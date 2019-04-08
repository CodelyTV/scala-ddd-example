CREATE TABLE videos (
  id                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  video_id            CHAR(36)            NOT NULL,
  title               VARCHAR(255)        NOT NULL,
  duration_in_seconds BIGINT(20) UNSIGNED NOT NULL,
  category            VARCHAR(255)        NOT NULL,
  creator_id          CHAR(36)            NOT NULL,
  updated_at          TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  UNIQUE KEY u_video_id (video_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE=utf8mb4_unicode_ci;
