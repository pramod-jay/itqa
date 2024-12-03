# Author Pramod Jayathilaka
# Date 03/12/2024
# Description Feature file for test create book API

Feature: createBook

  Scenario: Successfully create a book with all parameters
    Given the book details are:
      | id | title        | author      |
      | 3  | Harry Potter | J.K.Rowling |
    When I send a POST request to "/api/books"
    Then the response status code should be 201