CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cards (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    card_number INTEGER NOT NULL,
    card_holder VARCHAR(100) NOT NULL,
    cvv INTEGER NOT NULL,
    validity_date DATE,
    id_client UUID,
    FOREIGN KEY (id_client) REFERENCES clients(id) ON DELETE CASCADE
);