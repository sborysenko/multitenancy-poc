CREATE TABLE `auth_user` (
    id BIGINT auto_increment NOT NULL,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `user_tenant` (
    id BIGINT auto_increment NOT NULL,
    user_id BIGINT NULL,
    schema_name VARCHAR(100) NOT NULL,
    tenant_name VARCHAR(100) NOT NULL,
    CONSTRAINT user_tenant_pk PRIMARY KEY (id),
    CONSTRAINT user_tenant_FK FOREIGN KEY (user_id) REFERENCES `auth_user`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;