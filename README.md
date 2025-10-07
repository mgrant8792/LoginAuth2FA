# 🔐 LoginAuth2FA
A secure **Java console application** that demonstrates password hashing and simulated Two-Factor Authentication (2FA).  
Built by **Malcolm Grant**, Information Technology / Cybersecurity student at **Towson University**.

---

## 🧠 Overview
This project simulates how modern authentication systems protect user data using salted password hashing and a simple second-factor verification code (OTP).  
It’s a great introduction to practical cybersecurity concepts like encryption, salting, hashing, and credential storage.

---

## ⚙️ Features
✅ Password hashing with **PBKDF2WithHmacSHA256**  
✅ Randomized Base64 salt per user  
✅ Simple **file-based user database** (`users.db`)  
✅ 6-digit **simulated OTP** for 2FA  
✅ Console-based registration and login interface  
✅ Modular code: `AuthUtils`, `UserStore`, and `LoginApp` for separation of concerns  

---

## 🧩 Tech Stack
- **Language:** Java 17+  
- **Security:** `javax.crypto.SecretKeyFactory`, `PBEKeySpec`  
- **Storage:** Plain-text file database (for demo)  
- **IDE:** Visual Studio Code  
- **Version Control:** Git + GitHub  

---

## ▶️ How to Run
```bash
# 1. Compile
javac AuthUtils.java UserStore.java LoginApp.java

# 2. Run
java -cp . LoginApp