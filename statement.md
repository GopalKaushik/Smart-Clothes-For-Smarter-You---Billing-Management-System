# Project Statement

## Problem Statement
In fast-paced retail environments, manual billing systems are prone to human error, especially when calculating dynamic taxes like an 18% GST. Furthermore, traditional synchronous software often freezes or lags while writing receipt data to a hard drive or printer, forcing the cashier and the next customer to wait. There is also the constant risk of application crashes when cashiers accidentally type letters instead of numbers or enter invalid product codes during a rush. I wanted to solve these issues by building a fault-tolerant system that processes transactions instantly and handles background tasks without interrupting the user.

## Scope of the Project
This project is a localized, command-line Point of Sale (POS) application designed specifically for a clothing and accessories retail store. The scope includes managing a categorized product catalog in memory, handling a dynamic shopping cart, calculating subtotals with automated GST logic, and capturing basic customer CRM data (Name and Mobile). To keep the architecture lightweight and strictly aligned with core Java concepts, it utilizes Java Collections (`HashMap` and `ArrayList`) and File I/O for persistent storage (`receipts_log.txt`) instead of a heavy relational database.

## Target Users
* **Retail Cashiers:** Who need a fast, keyboard-driven interface to scan/enter product codes and checkout customers rapidly without system lag.
* **Store Managers/Administrators:** Who require accurate, automated tax calculations and a reliable, tamper-proof text log of all daily transactions.

## High-Level Features
* **OOP-Driven Catalog:** Products are logically divided into `WesternWear`, `EthnicWear`, and `Accessory` categories using Java Inheritance and Polymorphism.
* **Zero-Latency Lookups:** Implements `HashMap` data structures to ensure $O(1)$ constant-time retrieval for product codes, eliminating search delays.
* **Asynchronous Multithreading:** Spawns a dedicated background `Thread` for receipt generation and file I/O, allowing the main terminal to instantly reset for the next customer.
* **Fault-Tolerant Input Validation:** Uses custom exceptions (e.g., `InvalidProductCodeException`) and `try-catch` blocks to gracefully reject typos and invalid inputs without crashing the application.
* **Automated Invoice Engine:** Automatically computes 18% GST and formats a professional, timestamped receipt containing customer details and the itemized cart.
