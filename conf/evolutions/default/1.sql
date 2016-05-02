# Users schema

# --- !Ups

CREATE TABLE `admin` (
  `id`            BIGSERIAL        PRIMARY KEY      NOT NULL        AUTO_INCREMENT,
  `email`         VARCHAR(255)                      NOT NULL,
  `digest`        VARCHAR(255)                      NOT NULL,
  `created_at`    TIMESTAMP                         NOT NULL,
  `updated_at`    TIMESTAMP                         NOT NULL,

  UNIQUE KEY `email_uk` (`email`)
);

# --- !Downs

DROP TABLE admin;