CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE bill(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    owner VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    category VARCHAR(50) NOT NULL,
    type VARCHAR(50) NULL,
    amount NUMERIC NOT NULL,
    finish_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);