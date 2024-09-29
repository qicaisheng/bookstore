CREATE TABLE shopping_book (
    id VARCHAR(40) PRIMARY KEY,
    user_id VARCHAR(40) NOT NULL,
    book_id VARCHAR(40) NOT NULL,
    number INT NOT NULL
);

CREATE INDEX user_idx ON shopping_book (user_id);
CREATE INDEX book_idx ON shopping_book (book_id);