CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE addresses (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    id_client UUID,
    FOREIGN KEY (id_client) REFERENCES clients(id) ON DELETE CASCADE
);