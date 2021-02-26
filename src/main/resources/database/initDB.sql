CREATE TABLE IF NOT EXISTS customers
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(50)   NOT NULL,
    last_name  VARCHAR(100)  NOT NULL,
    date_of_birth DATE NOT NULL,
    address    VARCHAR(1000) NOT NULL,
    budget     DECIMAL       NOT NULL
);