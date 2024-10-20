CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE products (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(10, 2),
    final_price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(255) NOT NULL,
    storage_quantity INTEGER NOT NULL,
    description TEXT,
    img_url TEXT NOT NULL
);