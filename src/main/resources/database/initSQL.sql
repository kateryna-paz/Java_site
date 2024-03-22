-- Створюємо структуру бази дсних (таблиці для Продукції, Групи Продукції та Фірми Продукції)

CREATE DATABASE IF NOT EXISTS production;
USE production;

CREATE TABLE Product_Firm (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL
);

CREATE TABLE Product_Group (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(255) NOT NULL
);

CREATE TABLE Product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         date DATE NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         prod_group_id INT,
                         product_firm_id INT,
                         FOREIGN KEY (prod_group_id) REFERENCES Product_Group(id),
                         FOREIGN KEY (product_firm_id) REFERENCES Product_Firm(id)
);
