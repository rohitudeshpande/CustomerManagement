# CustomerManagement

This Spring Boot application manages customer records in an in-memory H2 database.

---

## 📌 APIs Offered

### 1. `POST /addCustomer`

Adds a new customer to the database.

**Request Payload**:
```json
{
  "name": "Rohit",
  "lastName": "Deshpande",
  "address": "Pune",
  "dateOfBirth": "14-09-1988",
  "socialSecurityNumber": "abcd12354"
}
```

**Authentication**:
- Uses **Basic Authentication**.
- Credentials should be configured in `application.properties`.
- Only users with the **admin** role can access this endpoint.

---

### 2. `GET /getCustomer/{socialSecurityNumber}`

Fetches customer details using their Social Security Number.

**Access**:
- Available to both **admin** and **readonly** users.

---

### 3. `GET /getCustomerById/{id}`

Fetches customer details using their unique customer ID.

**Access**:
- Available to both **admin** and **readonly** users.

---
