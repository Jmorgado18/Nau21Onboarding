
INSERT INTO customers (customer_name, reference) VALUES
                                                     ('John Doe', 'REF123'),
                                                     ('Jane Smith', 'REF456'),
                                                     ('Alice Johnson', 'REF789');


INSERT INTO shipping_addresses (street, city, country_code, zip_code) VALUES
                                                                          ('123 Elm Street', 'Springfield', 'US', '12345'),
                                                                          ('456 Oak Avenue', 'Shelbyville', 'US', '67890'),
                                                                          ('789 Pine Road', 'Capital City', 'CA', 'A1B2C3');


INSERT INTO items (name, description, price, currency, quantity) VALUES
                                                                     ('Widget', 'A useful widget', 19.99, 'USD', 100),
                                                                     ('Gadget', 'An innovative gadget', 29.99, 'USD', 50),
                                                                     ('Tool', 'A handy tool', 9.99, 'USD', 200);


INSERT INTO item_options (option_name, option_value, item_id) VALUES
                                                                  ('COLOR', 'RED', 1),
                                                                  ('COLOR', 'BLUE', 1),
                                                                  ('SIZE', 'LARGE', 2),
                                                                  ('SIZE', 'MEDIUM', 2),
                                                                  ('MATERIAL', 'METAL', 3),
                                                                  ('MATERIAL', 'PLASTIC', 3);


INSERT INTO orders (customer_id, shipping_address_id) VALUES
                                                          (1, 1),
                                                          (2, 2),
                                                          (3, 3);


INSERT INTO order_items (order_id, item_id, quantity) VALUES
                                                          (1, 1, 2),
                                                          (1, 2, 1),
                                                          (2, 2, 3),
                                                          (2, 3, 5),
                                                          (3, 1, 4),
                                                          (3, 3, 1);
