CREATE TABLE Clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    passwords VARCHAR(255),
    balance DECIMAL,
    type_account VARCHAR(50)
);

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    t_Value DECIMAL,
    client_id BIGINT,
    company_id BIGINT,
    purchased_foods VARCHAR(255),
    times TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES Clientes(id),
    FOREIGN KEY (company_id) REFERENCES Clientes(id)
);

CREATE TABLE fast_food (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    food_name VARCHAR(255) UNIQUE,
    description VARCHAR(255),
    stock INT,
    price DECIMAL
);