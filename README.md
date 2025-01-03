# IT Quality Assurance Project - IS3440

![QA Testing](https://img.shields.io/badge/QA-Testing-blue) ![Java](https://img.shields.io/badge/Java-v23-orange) ![Allure Report](https://img.shields.io/badge/Allure-2.12.0-green)

## Overview 
This project is an assignment for the **IS3440 - IT Quality Assurance** module. We implemented test cases for both **UI** and **API** testing using advanced testing tools and frameworks. 

### Project Highlights:
- **UI Tests**: Conducted on the OrangeHRM Demo site.
  - Link: [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewEvents)
- **API Tests**: Focused on a simple library management system hosted on Azure.
  - Server URL: [Azure Web App](https://itqabe-ewdyc2h4gra0fae4.eastus-01.azurewebsites.net)

---

## Technologies Used

![Selenium](https://img.shields.io/badge/Selenium-4.27.0-yellow) ![TestNG](https://img.shields.io/badge/TestNG-7.9.0-brightgreen) ![RestAssured](https://img.shields.io/badge/RestAssured-5.5.0-blue) ![Cucumber](https://img.shields.io/badge/Cucumber-7.20.1-lightgreen) ![Allure](https://img.shields.io/badge/Allure-2.12.0-purple)

---

## Prerequisites

1. **Java**: Version 23
2. **Maven**: Ensure it is properly configured.

---

## Getting Started

### Step 1: Clone the Repository
```bash
git clone https://github.com/pramod-jay/itqa_Group21.git
```

### Step 2: Run All Tests
Navigate to the project root and execute:
```bash
mvn test
```

### Step 3: Generate Allure Report
```bash
mvn allure:report
```

### Step 4: Serve the Report Locally
```bash
mvn allure:serve
```

### Running Tests Individually
- **UI Tests**: Run `src/test/runner/TestRunnerUI.java`
- **API Tests**: Run `src/test/runner/TestRunnerAPI.java`
- **Feature-Specific**: Execute individual feature files located in `src/test/resources/features/(api/ui)`

---

## UI Testing Details

UI testing was conducted on the OrangeHRM Demo site to ensure the user interface functions correctly under various scenarios. 

---

## API Testing Details

#### **API Credentials**
- **Username**: admin / user
- **Password**: password

Admin user can access all 4 APIs and invoke functionality. User can access post, get APIs.

### API Endpoints

#### **Retrieve Books**
- **GET /api/books**: Retrieve all books
- **GET /api/books/{id}**: Retrieve a book by ID

#### **Create Book**
- **POST /api/books**: Create a new book

**Request:**
```json
{
    "id": 123,
    "title": "Book Title",
    "author": "Author Name"
}
```

#### **Update Book**
- **PUT /api/books/{id}**: Update an existing book by ID

**Request:**
```json
{
    "id": 123,
    "title": "Updated Title",
    "author": "Updated Author"
}
```

#### **Delete Book**
- **DELETE /api/books/{id}**: Delete a book by ID

---

### Response Codes

- **200**: Successfully updated/deleted the book
- **201**: Successfully created the book
- **400**: Invalid | Empty Input Parameters in the Request
- **401**: You are not authorized to create the book
- **403**: Request api call is forbidden
- **404**: Book is not found

---

## CI/CD Integration
We integrated a CI/CD pipeline using Maven, and Allure reports are available via GitHub Pages.

### Allure Report Link:
[View Allure Report](https://pramod-jay.github.io/itqa_Group21/)

---

## Test Case Summary
- **Total Test Cases**: 39
  - UI Tests: 17
  - API Tests: 22

---

## Collaborators

### Pramod Jayathilaka  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/pramod-jayathilaka-b57178137)  [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/pramod-jay)  

### Lasantha Pradeep  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/lasantha-pradeep-b33939223)  [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/lasantha117)  

### Buddima Dissanayake  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/buddima-dissanayake-175381266)  [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/BuddimaDissanayake)  

### Nathali Fernando  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/nathali-fernando-69aa74248)  [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/NathaliFdo)  

### Erandi Malshika  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/erandimalshika)  [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ErandiMalshika)  

### Paramee Godage  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/paramee-godage-26b8b227a)  [![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/205072M)  

---

**Happy Testing!** :sparkles: :computer:

