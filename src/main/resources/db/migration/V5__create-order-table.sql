CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE orders (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    price DECIMAL(10, 2) NOT NULL,
    discount INTEGER,
    final_price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    address VARCHAR(255),
    address_complement VARCHAR(100),
    order_date DATE NOT NULL,
    status VARCHAR(100),
    id_client UUID,
    id_card UUID,
    id_address UUID,
    FOREIGN KEY (id_client) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (id_card) REFERENCES cards(id) ON DELETE SET NULL,
    FOREIGN KEY (id_address) REFERENCES addresses(id) ON DELETE SET NULL
);