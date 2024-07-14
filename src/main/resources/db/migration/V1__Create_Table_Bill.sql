CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS bill(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    owner VARCHAR(50) NOT NULL,
    amount NUMERIC NOT NULL,
    category VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    finish_at TIMESTAMP NOT NULL,
    type VARCHAR(50) NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);