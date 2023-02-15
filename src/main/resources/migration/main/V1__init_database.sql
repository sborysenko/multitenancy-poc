DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                          `code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
