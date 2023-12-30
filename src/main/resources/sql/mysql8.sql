-- 账户表
CREATE TABLE `t_account`
(
    `id`          bigint      NOT NULL,
    `username`    varchar(50) NOT NULL,
    `password`    varchar(25) NOT NULL,
    `deleted`     tinyint(1) NOT NULL,
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_un` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;