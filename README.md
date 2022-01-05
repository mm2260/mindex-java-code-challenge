# Coding Challenge
## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

---

### How to Run
The application may be executed by running `gradlew bootRun`.

---

### How to Use
The following endpoints are available to use:

### Employee:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```

The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

### Reporting Structure:
```
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}/reporting
    * RESPONSE: Reporting Structure
```

The Reporting Structure entity has a JSON schema of:
```json
{
  "type":"ReportingStructure",
  "properties": {
    "employee" : {
      "type" : "object" /*Employee*/
    },
    "numberOfReports" : {
      "type" : "int"
    }
  }
}
```

### Compensation:

```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/compensation
    * PAYLOAD: Compensation ( Employee, Salary, Date )
    * RESPONSE: Compensation
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/compensation/{id}
    * RESPONSE: Compensation
```
The Compensation entity has a JSON schema of:
```json
{
  "type":"Compensation",
  "properties": {
    "employee" : {
      "type" : "object" /*Employee*/
    },
    "salary" : {
      "type" : "number"
    },
    "effectiveDate" : {
      "type" : "string"
    }
  }
}
```

## What to Implement
Clone or download the repository, do not fork it.

---
### Task 1


_**Prompt:**_
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

#### Unit Test:

```test: com.mindex.challenge.service.impl.ReportingStructureServiceImplTest```

---

### Task 2

_**Prompt:**_
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

#### Unit Test:

```test: com.mindex.challenge.service.impl.CompensationServiceImplTest```
