# 🛍️ Smart Clothes For Smarter You — Billing Management System

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Course](https://img.shields.io/badge/Course-CSE2006--Java-007ACC?style=for-the-badge)
![Specialization](https://img.shields.io/badge/Branch-CSE%20AI%2FML-8A2BE2?style=for-the-badge)
![Status](https://img.shields.io/badge/Submission-Ready-brightgreen?style=for-the-badge)

Welcome to my Java Flipped Course Project! Instead of building a standard calculator or basic script, I wanted to engineer a real-world Point of Sale (POS) terminal for an apparel store[cite: 4]. This system doesn't just add numbers—it uses multithreading to generate receipts in the background, HashMaps for instant product lookups, and custom exceptions to survive bad terminal inputs without crashing[cite: 4]. 

---

## 👤 Developer Profile

| Parameter | Student Details |
| :--- | :--- |
| **Developer Name** | **Gopal Kaushik**[cite: 4] |
| **Registration Number** | **25BAI10814**[cite: 4] |
| **Degree** | B.Tech[cite: 4] |
| **Branch / Specialization** | Computer Science and Engineering (AI & ML)[cite: 4] |
| **Course Code & Title** | **CSE2006 — Programming in Java**[cite: 4] |

---

## 🌟 Why I Built It This Way (Key Features)

* 🏷️ **Smart Inventory Taxonomy**: I didn't want a flat list, so I modeled a categorized apparel catalog using OOP principles, breaking items down into `WesternWear`, `EthnicWear`, and `Accessory` modules[cite: 4].
* ⚡ **Zero-Latency Lookups**: Nobody likes a slow checkout. I used Java `HashMap` data structures so searching for a product code takes $O(1)$ time[cite: 4]. 
* 📋 **Real-World CRM**: Because an actual store needs customer data, the checkout flow dynamically prompts for and captures the customer's Name and Mobile Number[cite: 4].
* 🧮 **Automated Tax Engine**: No manual math required. The system computes accurate subtotals and automatically slaps on the standard 18% GST before checkout[cite: 4].
* 🧵 **Asynchronous Receipt Logging (The Cool Part)**: Writing to a file usually freezes the terminal. I spawned a dedicated background `ReceiptGeneratorThread` to handle the file I/O[cite: 4]. It writes the invoice to `receipts_log.txt` asynchronously, meaning the cashier can immediately start helping the next customer[cite: 4].
* 🛡️ **Bulletproof Inputs**: I hate when a typo crashes a whole program. I built custom exception classes (like `InvalidProductCodeException`) and wrapped them in `try-catch` blocks to gracefully reject bad inputs[cite: 4].

---

## 🎓 How It Hits the CSE2006 Syllabus

I made sure this project strictly aligns with our core Java curriculum:

| Java Concept | Where I Used It |
| :--- | :--- |
| **Abstract Classes & Inheritance** | Created a base `Product` class, which is extended by the specific clothing categories[cite: 4] |
| **Polymorphism** | Overrode the `getDetails()` method across the different child classes to format their display[cite: 4] |
| **Collections Framework** | Used `HashMap` for the inventory and a dynamic `ArrayList` for the shopping cart[cite: 4] |
| **Multithreading** | Engineered the `ReceiptGeneratorThread` extending `Thread` for non-blocking file writing[cite: 4] |
| **Custom Exception Handling** | Designed `InvalidProductCodeException` to catch and handle illegal product codes[cite: 4] |
| **Input / Output Streams** | Leveraged `FileWriter` and `PrintWriter` to append physical receipts to `receipts_log.txt`[cite: 4] |

---

## 🛠️ What's Under the Hood

* **Language**: Java 8 or higher[cite: 4]
* **Development Tools**: Standard CLI Terminal / Any Java IDE (VS Code, IntelliJ IDEA, Eclipse)[cite: 4]
* **Libraries**: Native Java Standard Libraries (`java.util.*`, `java.io.*`, `java.time.*`)[cite: 4]

---

## 📂 Project Architecture

```text
.
├── BillingSystemMain.java   # The single consolidated source file containing all my class definitions[cite: 4]
├── receipts_log.txt        # The persistent text file where all invoices are automatically saved[cite: 4]
└── README.md                # You are here![cite: 4]
