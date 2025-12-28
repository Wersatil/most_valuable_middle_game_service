CREATE TABLE question (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    question_text varchar(1000) NOT NULL,
    answer varchar(8000) NOT NULL,
    author varchar(64) NOT NULL
);
