-- Create the products table for school supplies
CREATE TABLE IF NOT EXISTS school_supplies (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    price REAL NOT NULL,
    stock INTEGER NOT NULL
);

-- Create a table to track purchases
CREATE TABLE IF NOT EXISTS orders (
    order_id INTEGER PRIMARY KEY AUTOINCREMENT,
    item_id INTEGER,
    quantity_purchased INTEGER,
    total_cost REAL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (item_id) REFERENCES school_supplies(id)
);

-- Insert starter items
INSERT INTO school_supplies (name, category, price, stock) VALUES
('Scientific Calculator', 'Electronics', 350.00, 15),
('Hardcover Notebook A4', 'Stationery', 25.50, 100),
('Blue Gel Pens (Pack of 4)', 'Stationery', 45.00, 50),
('Ergonomic Backpack', 'Bags', 499.99, 10),
('Math Geometry Set', 'Stationery', 60.00, 30);