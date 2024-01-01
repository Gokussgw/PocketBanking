
-- Create 'customer' table
CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(15),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create 'account' table
CREATE TABLE account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    account_type VARCHAR(50),
    balance DECIMAL(15, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Create 'transaction' table
CREATE TABLE transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    type ENUM('deposit', 'withdrawal', 'transfer'),
    amount DECIMAL(15, 2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(account_id)
);

-- Create 'employee' table
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    position VARCHAR(100),
    department VARCHAR(100),
    hire_date DATE
);

-- Delimiter setting for creating procedure
DELIMITER $$

-- Procedure for transferring funds
CREATE PROCEDURE transferFunds(IN source_account_id INT, IN target_account_id INT, IN transfer_amount DECIMAL(15,2))
BEGIN
    DECLARE source_balance DECIMAL(15,2);
    SELECT balance INTO source_balance FROM account WHERE account_id = source_account_id;

    IF source_balance >= transfer_amount THEN
        UPDATE account SET balance = balance - transfer_amount WHERE account_id = source_account_id;
        UPDATE account SET balance = balance + transfer_amount WHERE account_id = target_account_id;

        INSERT INTO transaction (account_id, type, amount) VALUES (source_account_id, 'withdrawal', transfer_amount);
        INSERT INTO transaction (account_id, type, amount) VALUES (target_account_id, 'deposit', transfer_amount);
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient funds in source account';
    END IF;
END$$

DELIMITER ;

-- Example usage (to be executed separately):
-- CALL transferFunds(1, 2, 100.00);

-- Query to retrieve transaction history (to be executed separately):
-- SELECT * FROM transaction WHERE account_id = [Your_Account_ID] ORDER BY transaction_date DESC;
