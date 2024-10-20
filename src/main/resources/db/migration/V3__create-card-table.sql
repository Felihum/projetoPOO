CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cards (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    card_number INTEGER NOT NULL,
    card_holder VARCHAR(100) NOT NULL,
    cvv INTEGER NOT NULL,
    validity_date DATE,
    id_user UUID,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);