# 🎒 School Shopping System

A lightweight, terminal-based enterprise application built with **Kotlin** and **SQL (SQLite)** inside **Visual Studio Code**. This system provides an interactive console interface to browse, select, and purchase essential academic supplies while maintaining real-time data integrity via transactional database updates.

---

## ✨ Features

* **Real-Time Catalog Management:** Instantly pulls live stock data, categories, and pricing directly from an SQLite database.
* **ACID-Compliant Transactions:** Built with secure commit and rollback structures to guarantee accurate ledger tracking and prevent data duplication or corrupt stock updates.
* **Dependency-Free Bootstrap:** Leveraging Kotlin Script (`.main.kts`), eliminating heavy build configurations like Gradle or Maven for smaller deployment sizes.
* **Dynamic Visual Reports:** Designed with structured tables cleanly formatted for terminal displays using custom layout dimensions.

---

## 🛠️ Project Workspace Architecture

```bash
SchoolShoppingSystem/
├── app.main.kts          # Main Kotlin source application script & dependency resolver
├── school_shop.db        # Core SQLite database storage (Generated on initial run)
└── README.md             # Systems documentation and setup manual
