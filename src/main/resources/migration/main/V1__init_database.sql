CREATE TABLE `company` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `user` (
    id BIGINT AUTO_INCREMENT NOT NULL,
    company_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT user_company_FK FOREIGN KEY (company_id) REFERENCES `company`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

