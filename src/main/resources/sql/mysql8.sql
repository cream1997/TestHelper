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
-- 用户表
CREATE TABLE `t_user`
(
    `id`          bigint      NOT NULL,
    `username`    varchar(50) NOT NULL,
    `password`    varchar(50) NOT NULL,
    `deleted`     tinyint(1) NOT NULL,
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_user_un` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 远端用户模拟表
CREATE TABLE `t_mock_remote_user`
(
    `id`       bigint      NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_mock_remote_user_un` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 角色模拟表
CREATE TABLE `t_mock_role`
(
    `id`          bigint      NOT NULL,
    `user_id`     bigint      NOT NULL,
    `name`        varchar(50) NOT NULL,
    `level`       int         NOT NULL,
    `career`      int         NOT NULL,
    `deleted`     tinyint(1) NOT NULL,
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_mock_role_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_account_user`
(
    `id`          bigint       NOT NULL,
    `accountId`   bigint       NOT NULL,
    `userId`      varchar(100) NOT NULL,
    `deleted`     tinyint(1) NOT NULL DEFAULT '0',
    `create_time` datetime     NOT NULL,
    `update_time` datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_account_user_unique` (`accountId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;