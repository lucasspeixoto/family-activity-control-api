CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE category(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);