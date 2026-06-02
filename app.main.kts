#!/usr/bin/env kotlin

// This line automatically downloads the SQL driver so you don't need Gradle setup!
@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("org.xerial:sqlite-jdbc:3.46.0.0")

import java.sql.Connection
import java.sql.DriverManager
import java.util.Scanner

val scanner = Scanner(System.`in`)
val url = "jdbc:sqlite:school_shop.db"

try {
    DriverManager.getConnection(url).use { conn ->
        // Auto-create tables if they don't exist yet
        initDatabase(conn)
        
        println("=== Welcome to the School Shopping System ===")
        var running = true
        
        while (running) {
            println("\n1. View Available Supplies")
            println("2. Buy an Item")
            println("3. Exit")
            print("Choose an option: ")
            
            when (if (scanner.hasNextInt()) scanner.nextInt() else 3) {
                1 -> displaySupplies(conn)
                2 -> purchaseItem(conn, scanner)
                3 -> {
                    println("Thank you for shopping with us!")
                    running = false
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
} catch (e: Exception) {
    println("Database error: ${e.message}")
}

fun initDatabase(conn: Connection) {
    val createSupplies = """
        CREATE TABLE IF NOT EXISTS school_supplies (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            category TEXT NOT NULL,
            price REAL NOT NULL,
            stock INTEGER NOT NULL
        );
    """
    val createOrders = """
        CREATE TABLE IF NOT EXISTS orders (
            order_id INTEGER PRIMARY KEY AUTOINCREMENT,
            item_id INTEGER,
            quantity_purchased INTEGER,
            total_cost REAL,
            order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """
    conn.createStatement().use { stmt ->
        stmt.execute(createSupplies)
        stmt.execute(createOrders)
        
        // Check if empty, insert seed data
        val rs = stmt.executeQuery("SELECT COUNT(*) FROM school_supplies")
        if (rs.next() && rs.getInt(1) == 0) {
            stmt.execute("INSERT INTO school_supplies (name, category, price, stock) VALUES ('Scientific Calculator', 'Electronics', 350.00, 15)")
            stmt.execute("INSERT INTO school_supplies (name, category, price, stock) VALUES ('Hardcover Notebook A4', 'Stationery', 25.50, 100)")
            stmt.execute("INSERT INTO school_supplies (name, category, price, stock) VALUES ('Blue Gel Pens (Pack of 4)', 'Stationery', 45.00, 50)")
        }
    }
}

fun displaySupplies(conn: Connection) {
    val query = "SELECT * FROM school_supplies"
    println("\n--- Available School Supplies ---")
    println(String.format("%-4s | %-25s | %-12s | %-8s | %-6s", "ID", "Name", "Category", "Price", "Stock"))
    println("------------------------------------------------------------------------")
    
    conn.createStatement().use { stmt ->
        stmt.executeQuery(query).use { rs ->
            while (rs.next()) {
                println(String.format("%-4d | %-25s | %-12s | R%-7.2f | %-6d",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ))
            }
        }
    }
}

fun purchaseItem(conn: Connection, scanner: Scanner) {
    print("\nEnter the Item ID you want to buy: ")
    val itemId = scanner.nextInt()
    print("Enter quantity: ")
    val quantity = scanner.nextInt()
    
    val checkQuery = "SELECT name, price, stock FROM school_supplies WHERE id = ?"
    
    conn.prepareStatement(checkQuery).use { pstmt ->
        pstmt.setInt(1, itemId)
        pstmt.executeQuery().use { rs ->
            if (!rs.next()) {
                println("Item not found!")
                return
            }
            
            val name = rs.getString("name")
            val price = rs.getDouble("price")
            val currentStock = rs.getInt("stock")
            
            if (currentStock < quantity) {
                println("Sorry, insufficient stock! Only $currentStock left.")
                return
            }
            
            val totalCost = price * quantity
            conn.autoCommit = false
            try {
                conn.prepareStatement("UPDATE school_supplies SET stock = stock - ? WHERE id = ?").use { updateStmt ->
                    updateStmt.setInt(1, quantity)
                    updateStmt.setInt(2, itemId)
                    updateStmt.executeUpdate()
                }
                
                conn.prepareStatement("INSERT INTO orders (item_id, quantity_purchased, total_cost) VALUES (?, ?, ?)").use { orderStmt ->
           #!/usr/bin/env kotlin

// This line automatically downloads the SQL driver so you don't need Gradle setup!
@file:Repository("[https://repo1.maven.org/maven2/](https://repo1.maven.org/maven2/)")
@file:DependsOn("org.xerial:sqlite-jdbc:3.46.0.0")

import java.sql.Connection
import java.sql.DriverManager
import java.util.Scanner

val scanner = Scanner(System.`in`)
val url = "jdbc:sqlite:school_shop.db"

try {
    DriverManager.getConnection(url).use { conn ->
        // Auto-create tables if they don't exist yet
        initDatabase(conn)
        
        println("=== Welcome to the School Shopping System ===")
        var running = true
        
        while (running) {
            println("\n1. View Available Supplies")
            println("2. Buy an Item")
            println("3. Exit")
            print("Choose an option: ")
            
            when (if (scanner.hasNextInt()) scanner.nextInt() else 3) {
                1 -> displaySupplies(conn)
                2 -> purchaseItem(conn, scanner)
                3 -> {
                    println("Thank you for shopping with us!")
                    running = false
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
} catch (e: Exception) {
    println("Database error: ${e.message}")
}

fun initDatabase(conn: Connection) {
    val createSupplies = """
        CREATE TABLE IF NOT EXISTS school_supplies (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            category TEXT NOT NULL,
            price REAL NOT NULL,
            stock INTEGER NOT NULL
        );
    """
    val createOrders = """
        CREATE TABLE IF NOT EXISTS orders (
            order_id INTEGER PRIMARY KEY AUTOINCREMENT,
            item_id INTEGER,
            quantity_purchased INTEGER,
            total_cost REAL,
            order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """
    conn.createStatement().use { stmt ->
        stmt.execute(createSupplies)
        stmt.execute(createOrders)
        
        // Check if empty, insert seed data
        val rs = stmt.executeQuery("SELECT COUNT(*) FROM school_supplies")
        if (rs.next() && rs.getInt(1) == 0) {
            stmt.execute("INSERT INTO school_supplies (name, category, price, stock) VALUES ('Scientific Calculator', 'Electronics', 350.00, 15)")
            stmt.execute("INSERT INTO school_supplies (name, category, price, stock) VALUES ('Hardcover Notebook A4', 'Stationery', 25.50, 100)")
            stmt.execute("INSERT INTO school_supplies (name, category, price, stock) VALUES ('Blue Gel Pens (Pack of 4)', 'Stationery', 45.00, 50)")
        }
    }
}

fun displaySupplies(conn: Connection) {
    val query = "SELECT * FROM school_supplies"
    println("\n--- Available School Supplies ---")
    println(String.format("%-4s | %-25s | %-12s | %-8s | %-6s", "ID", "Name", "Category", "Price", "Stock"))
    println("------------------------------------------------------------------------")
    
    conn.createStatement().use { stmt ->
        stmt.executeQuery(query).use { rs ->
            while (rs.next()) {
                println(String.format("%-4d | %-25s | %-12s | R%-7.2f | %-6d",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ))
            }
        }
    }
}

fun purchaseItem(conn: Connection, scanner: Scanner) {
    print("\nEnter the Item ID you want to buy: ")
    val itemId = scanner.nextInt()
    print("Enter quantity: ")
    val quantity = scanner.nextInt()
    
    val checkQuery = "SELECT name, price, stock FROM school_supplies WHERE id = ?"
    
    conn.prepareStatement(checkQuery).use { pstmt ->
        pstmt.setInt(1, itemId)
        pstmt.executeQuery().use { rs ->
            if (!rs.next()) {
                println("Item not found!")
                return
            }
            
            val name = rs.getString("name")
            val price = rs.getDouble("price")
            val currentStock = rs.getInt("stock")
            
            if (currentStock < quantity) {
                println("Sorry, insufficient stock! Only $currentStock left.")
                return
            }
            
            val totalCost = price * quantity
            conn.autoCommit = false
            try {
                conn.prepareStatement("UPDATE school_supplies SET stock = stock - ? WHERE id = ?").use { updateStmt ->
                    updateStmt.setInt(1, quantity)
                    updateStmt.setInt(2, itemId)
                    updateStmt.executeUpdate()
                }
                
                conn.prepareStatement("INSERT INTO orders (item_id, quantity_purchased, total_cost) VALUES (?, ?, ?)").use { orderStmt ->
                    orderStmt.setInt(1, itemId)
                    orderStmt.setInt(2, quantity)
                    orderStmt.setDouble(3, totalCost)
                    orderStmt.executeUpdate()
                }
                
                conn.commit()
                println("\n🎉 Purchase Successful!")
                println("You bought $quantity x $name for a total of R${String.format("%.2f", totalCost)}")
                
            } catch (e: Exception) {
                conn.rollback()
                println("Transaction failed: ${e.message}")
            } finally {
                conn.autoCommit = true
            }
        }
    }
}         orderStmt.setInt(1, itemId)
                    orderStmt.setInt(2, quantity)
                    orderStmt.setDouble(3, totalCost)
                    orderStmt.executeUpdate()
                }
                
                conn.commit()
                println("\n🎉 Purchase Successful!")
                println("You bought $quantity x $name for a total of R${String.format("%.2f", totalCost)}")
                
            } catch (e: Exception) {
                conn.rollback()
                println("Transaction failed: ${e.message}")
            } finally {
                conn.autoCommit = true
            }
        }
    }
}