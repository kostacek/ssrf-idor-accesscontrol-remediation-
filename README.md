# Access Control and SSRF Remediation Project

This project demonstrates secure coding and access‑control remediation in a penetration‑testing training application. It covers mitigation of **Server‑Side Request Forgery (SSRF)**, **Insecure Direct Object Reference (IDOR)**, and **role‑based access control (RBAC)** vulnerabilities using **Java/Spring Boot** and **Apache Shiro**.

---

## 🧩 Project Overview
The application initially contained multiple injection and authorization flaws.  
This project implements three major fixes:

1. **SSRF Mitigation** – Validates and allowlists URLs before outbound requests.  
2. **IDOR Remediation** – Enforces object‑level authorization and session‑based access checks.  
3. **Access‑Control Enhancement** – Extends Apache Shiro configuration with a new role and permissions.

---

## 🔐 1. Fixing the SSRF Vulnerability
**Issue:** The backend fetched user‑supplied URLs without validation, allowing internal resource access (e.g., `file:///etc/passwd`, `http://169.254.169.254`).  
**Fix:**  
- Implemented strict URL parsing and scheme validation (`http`/`https` only).  
- Added host allowlisting (`api.internal.example.com`, `images.example.com`).  
- Disabled automatic redirects in the HTTP client.  
- Applied network‑layer restrictions to block internal endpoints.

---

## 🧠 2. Fixing IDOR and Access‑Control Vulnerabilities
**Issue:** Users could manipulate identifiers in URLs to access other users’ data or performance records.  
**Fix:**  
- Derived the current user from session context instead of trusting request parameters.  
- Queried objects through user‑scoped repositories (`findByIdAndUser`).  
- Implemented authorization checks before returning sensitive data.

---

## ⚙️ 3. Apache Shiro Role Configuration
**Objective:** Extend the Shiro configuration to include a new role and permissions.

### Added Role
- **Role:** `49sd`  
- **Permissions:**  
  - `tesla:drive:*`  
  - `winnebago:drive:*`

### Added User
- **User:** `billchu`  
- **Role:** `49sd`

### Execution Result
After running the Maven Quickstart sample:
- `billchu` successfully authenticated.  
- Shiro confirmed permissions for driving all Teslas and Winnebagos.  
- Role‑based access control enforced exactly as defined in `shiro.ini`.

---

## 📂 Repository Structure
