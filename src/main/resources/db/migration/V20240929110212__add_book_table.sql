CREATE TABLE book (
    id VARCHAR(40) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(20) NOT NULL,
    price_value DECIMAL(10, 2) NOT NULL,
    price_currency VARCHAR(4) NOT NULL,
    category VARCHAR(10) NOT NULL
);