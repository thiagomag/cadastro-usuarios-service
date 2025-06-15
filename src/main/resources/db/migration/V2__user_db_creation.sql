CREATE TABLE users
(
    id                       BIGSERIAL       PRIMARY KEY NOT NULL,
    email                    varchar(255)    UNIQUE NOT NULL,
    password                 varchar(100)    NOT NULL,
    name                     varchar(255)    NOT NULL,
    employee_type            varchar(50)     NOT NULL,
    council_number           varchar(50),
    council_state            varchar(2),
    specialty                varchar(255),
    role_id                  BIGINT          NOT NULL,
    created_at               TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_at               TIMESTAMP,
    deleted_tmsp             TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);