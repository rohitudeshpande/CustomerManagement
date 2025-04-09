# CustomerManagement
This application manages customer database in in-memory database

# APIs offered
**1. /addCustomer**  
`POST` API that adds a customer to the database.

**Payload**:
```json
{
  "name": "Rohit",
  "lastName": "Deshpande",
  "address": "Pune",
  "dateOfBirth": "14-09-1988",
  "socialSecurityNumber": "abcd12354"
}
Authentication:
   Follows basic authentication. To use it, configure credentials in application.properties.
   Only Admin user has privilege to add a customer.

**2. /getCustomer/{socialSecurityNumber}**
   Fethches the Customer by social security number.
   Both admin user and readonly user have access.

**3. /getCustomerById/{id}**
   Fethches the Customer by customer id.
   Both admin user and readonly user have access.


