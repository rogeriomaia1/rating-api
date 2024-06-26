-- Criação do schema
CREATE SCHEMA rating;

-- Criação da tabela users com índice
CREATE TABLE rating.users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    roles VARCHAR(255)
);

CREATE INDEX idx_users_name ON rating.users(name);

-- Criação da tabela rating com índices
CREATE TABLE rating.rating (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    score INTEGER NOT NULL CHECK (score >= 1 AND score <= 5),
    comments TEXT,
    contact_number VARCHAR(20),
    contact_time VARCHAR(50),
    contact_request BOOLEAN
);

CREATE INDEX idx_rating_email ON rating.rating(email);
CREATE INDEX idx_rating_score ON rating.rating(score);



INSERT INTO rating.users (email, password, name, roles) VALUES
('admin@datapar.com', 'admin', 'Admin User', 'ROLE_ADMIN');
