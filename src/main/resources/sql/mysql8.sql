-- 账户表
CREATE TABLE `t_account`
(
    `id`           bigint      NOT NULL,
    `account_name` varchar(50) NOT NULL,
    `password`     varchar(50) NOT NULL,
    `deleted`      tinyint(1)  NOT NULL,
    `create_time`  datetime    NOT NULL,
    `update_time`  datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_un` (`account_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
-- 用户表
CREATE TABLE `t_user`
(
    `id`          bigint      NOT NULL,
    `username`    varchar(50) NOT NULL,
    `password`    varchar(50) NOT NULL,
    `account_id`  bigint      NOT NULL,
    `deleted`     tinyint(1)  NOT NULL,
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_user_un` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 远端用户模拟表
CREATE TABLE `t_mock_remote_user`
(
    `id`       bigint      NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_mock_remote_user_un` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 角色模拟表
CREATE TABLE `t_mock_role`
(
    `id`          bigint      NOT NULL,
    `user_id`     bigint      NOT NULL,
    `name`        varchar(50) NOT NULL,
    `level`       int         NOT NULL,
    `career`      int         NOT NULL,
    `deleted`     tinyint(1)  NOT NULL,
    `create_time` datetime    NOT NULL,
    `update_time` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_mock_role_un` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- 账户设置表

CREATE TABLE `t_account_setup`
(
    `account_id`                bigint   NOT NULL,
    `default_server`            varchar(10)   DEFAULT NULL,
    `default_filter_cancel_msg` varchar(512)  DEFAULT NULL,
    `custom_filter_msg`         varchar(1024) DEFAULT NULL,
    `create_time`               datetime NOT NULL,
    `update_time`               datetime NOT NULL,
    PRIMARY KEY (`account_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;