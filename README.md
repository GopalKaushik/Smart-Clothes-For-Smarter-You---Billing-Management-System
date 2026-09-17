# Smart-Clothes-For-Smarter-You---Billing-Management-System
# 🛍️ Smart Clothes For Smarter You — Billing Management System

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Course](https://img.shields.io/badge/Course-CSE2006--Java-007ACC?style=for-the-badge)
![Specialization](https://img.shields.io/badge/Branch-CSE%20AI%2FML-8A2BE2?style=for-the-badge)
![Status](https://img.shields.io/badge/Submission-Ready-brightgreen?style=for-the-badge)

A robust, multi-threaded command-line Point of Sale (POS) billing software engineered in Java. Designed for modern apparel retail, the system automates item catalog lookups, dynamic shopping carts, 18% GST tax computation, customer data capture, and asynchronous receipt persistence via background thread execution.

---

## 👤 Developer Profile

| Parameter | Student Details |
| :--- | :--- |
| **Developer Name** | **Gopal Kaushik** |
| **Registration Number** | **25BAI10814** |
| **Degree** | B.Tech |
| **Branch / Specialization** | Computer Science and Engineering (AI & ML) |
| **Course Code & Title** | **CSE2006 — Programming in Java** |

---

## 🌟 Key Features

* 🏷️ **Categorized Apparel Catalog**: Subdivided into `WesternWear`, `EthnicWear`, and `Accessory` modules.
* ⚡ **$O(1)$ Catalog Searching**: Powered by Java `HashMap` data structures for zero-latency item resolution.
* 📋 **Customer CRM Integration**: Captures customer identity (Name) and Mobile Number at the checkout stage.
* 🧮 **Automated Tax Calculation**: Computes accurate item subtotals along with standard 18% GST addition.
* 🧵 **Asynchronous Receipt Logging**: Uses a dedicated background thread (`ReceiptGeneratorThread`) to perform non-blocking file I/O operations into `receipts_log.txt`.
* 🛡️ **Fault Tolerant Architecture**: Custom exception classes (`InvalidProductCodeException`) combined with `try-catch` blocks safeguard against bad terminal inputs.

---

## 🎓 Syllabus Concepts Implemented

| Java Concept | Module / Implementation |
| :--- | :--- |
| **Abstract Classes & Inheritance** | Base class `Product` extended by `WesternWear`, `EthnicWear`, and `Accessory` |
| **Polymorphism** | Overridden `getDetails()` method across derived product classes |
| **Collections Framework** | `HashMap` for $O(1)$ inventory lookup and `ArrayList` for dynamic shopping cart |
| **Multithreading** | `ReceiptGeneratorThread` extending `Thread` for non-blocking file I/O |
| **Custom Exception Handling** | `InvalidProductCodeException` thrown on illegal product code entry |
| **Input / Output Streams** | `FileWriter` and `PrintWriter` for appending transactions to `receipts_log.txt` |

---

## 🛠️ Tech Stack & Prerequisites

* **Language**: Java 8 or higher
* **Development Tools**: Standard CLI Terminal / Any Java IDE (VS Code, IntelliJ IDEA, Eclipse)
* **Libraries**: Native Java Standard Libraries (`java.util.*`, `java.io.*`, `java.time.*`)

---

## 📂 Project Architecture

```text
.
├── BillingSystemMain.java   # Consolidated source file containing all class definitions
├── receipts_log.txt        # Auto-generated persistent transaction log file
└── README.md                # Project documentation and developer metadata
