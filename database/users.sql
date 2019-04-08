CREATE TABLE users (
  id         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id    CHAR(36)            NOT NULL,
  name       VARCHAR(255)        NOT NULL,
  updated_at TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  UNIQUE KEY u_user_id (user_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE=utf8mb4_unicode_ci;
