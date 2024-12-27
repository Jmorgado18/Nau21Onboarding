
DROP DATABASE IF EXISTS ordermanagement;


CREATE DATABASE ordermanagement;
USE ordermanagement;


CREATE TABLE items (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT NOT NULL,
                       price DECIMAL(10, 2) NOT NULL,
                       currency VARCHAR(10) NOT NULL,
                       quantity INT NOT NULL DEFAULT 0
);


CREATE TABLE customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           customer_name VARCHAR(255) NOT NULL,
                           reference VARCHAR(255)
);


CREATE TABLE shipping_addresses (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    street VARCHAR(255) NOT NULL,
                                    city VARCHAR(255) NOT NULL,
                                    country_code CHAR(2) NOT NULL,
                                    zip_code VARCHAR(20) NOT NULL
);


CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        customer_id INT NOT NULL,
                        shipping_address_id INT NOT NULL,
                        FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
                        FOREIGN KEY (shipping_address_id) REFERENCES shipping_addresses(id) ON DELETE CASCADE
);


CREATE TABLE item_options (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              option_name VARCHAR(255) NOT NULL,
                              option_value VARCHAR(255) NOT NULL,
                              item_id INT NOT NULL,
                              FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);


CREATE TABLE order_items (
                             order_id INT NOT NULL,
                             item_id INT NOT NULL,
                             quantity INT NOT NULL DEFAULT 0,
                             PRIMARY KEY (order_id, item_id),
                             FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                             FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);
