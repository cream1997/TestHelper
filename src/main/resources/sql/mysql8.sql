-- helper.account definition

CREATE TABLE helper.account
(
    id       BIGINT      NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(25) NOT NULL,
    CONSTRAINT account_pk PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;