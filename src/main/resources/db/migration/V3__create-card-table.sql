CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cards (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    card_number VARCHAR(100) NOT NULL,
    card_holder VARCHAR(100) NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    validity_month INTEGER,
    validity_year INTEGER,
    id_client UUID,
    FOREIGN KEY (id_client) REFERENCES clients(id) ON DELETE CASCADE
);