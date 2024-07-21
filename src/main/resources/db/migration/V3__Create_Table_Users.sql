CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS users (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  email VARCHAR(50) DEFAULT NULL,
  name VARCHAR(50) DEFAULT NULL,
  password VARCHAR(255) DEFAULT NULL,
  username VARCHAR(50) DEFAULT NULL
);