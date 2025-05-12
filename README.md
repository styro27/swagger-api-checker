# Swagger API Checker

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

Automated testing framework for validating API endpoints against Swagger/OpenAPI specifications.

## Features

- Automated generation of POJO classes from Swagger JSON/YAML
- Validates live API endpoints against OpenAPI 3.0 specifications
- Automatically integrates request/response schema validation using `OpenApiValidationFilter`
- Parameterized testing
- Allure report integration

## Technologies Used
- Java 17

- JUnit 5

- REST Assured

- Swagger Request Validator (swagger-request-validator-restassured)

- Gradle

- Faker for generating test data

- Allure for report generation

## Installation

1. Clone the repository:
```bash
git clone https://github.com/styro27/swagger-api-checker.git
cd swagger-api-checker
```
2. Open project in your IDE

## Environment Configuration

The `${profile}-session.properties` contains URLs, logins, passwords, etc. for a specific environment. You can add parameters for the current environment or add a new config file for another one.


## Running Tests

- Run tests with default settings:
  ```bash
  gradle test -Pprofile=test

## Reports

The Allure library is used for reports. Test reports are generated in the `build/allure-results` directory.

- Generate a report after completing the tests and open it automatically in the browser:
  ```bash
   gradle allureServe
  ```
  ## Folder Structure
```
src/
├── main/
│   └── java/org/example/
│       ├── config/               # Environment config interface
│       ├── enums/                # Enums for endpoints
│       ├── extensions/           # REST client resolver
│       
└── test/
├── java/org/example/tests/utils  # Test utils
└── java/org/example/tests/  # Test classes using JUnit 5
```
## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.