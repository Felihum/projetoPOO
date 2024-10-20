CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE items (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    quantity INTEGER NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    id_product UUID,
    id_user UUID,
    id_order UUID,
    FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (id_order) REFERENCES orders(id) ON DELETE CASCADE
);