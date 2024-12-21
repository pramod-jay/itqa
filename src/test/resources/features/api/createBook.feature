# Author Pramod Jayathilaka
# Date 03/12/2024
# Description Feature file for test create book API

Feature: createBook
  @TestCaseId("API_TC001")
  Scenario: Successfully create a book with all parameters
    Given the book details are:
      | id | title        | author      |
      | 3  | Harry Potter | J.K.Rowling |
    When I send a POST request to "api/books"
    Then the response status code should be 201
    And the response should include the book details:
      | id     |
      | title  |
      | author |

  @TestCaseId("API_TC002")
  Scenario: Successfully create a book without id
    Given the book details are:
      | title       | author        |
      | Animal Farm | George Orwell |
    When I send a POST request to "api/books"
    Then the response status code should be 201
    And the response should include an auto-generated id

  @TestCaseId("API_TC003")
  Scenario: Unsuccessfully create an already entered book
    Given the book details are:
      | id | title        | author        |
      |    | Animal Farm  | George Orwell |
      | 3  | Harry Potter | J.K.Rowling   |
    When I send a POST request to "api/books"
    Then the response status code should be 208
    And the response should include a message "Book Already Exists"

  @TestCaseId("API_TC004")
  Scenario: Unsuccessfully create with invalid credentials
    Given the book details are:
      | title         | author       |
      | The Alchemist | Paulo Coelho |
    When I send a POST request to "api/books" with username "testUser" and password "Abcd@123"
    Then the response status code should be 401
    And the response should include a message "You are not authorized to create the book"

  @TestCaseId("API_TC005")
  Scenario Outline: Unsuccessfully create without mandatory parameter
    Given the book details are:
      | title  | author   |
      | <book> | <author> |
    When I send a POST request to "api/books"
    Then the response status code should be 400
    And the response should include a message "Invalid | Empty Input Parameters in the Request"

    Examples:
      | book              | author          |
      | The Da Vinci Code |                 |
      |                   | Stephenie Meyer |
      |                   |                 |

  @TestCaseId("API_TC006")
  Scenario: Successfully create with another book with same author
    Given the book details are:
      | title       | author        |
      | Burmese Days | George Orwell |
    When I send a POST request to "api/books"
    Then the response status code should be 201
    And the response should include the book details:
      | id     |
      | title  |
      | author |