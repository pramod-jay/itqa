# Author Nathali Fernando
# Date 28/12/2024
# Description Feature file for test update book API

Feature: updateBook
  As an admin of the library system
  I want to update the book details
  So that the updated information is reflected in the system

  Scenario: Successfully update a book
    Given a book exist in the system with ID 1
    When I send a PUT request to "api/books/" with following details:
      |id | title                         | author      |
      | 1 | Harry Potter - Goblet of Fire | J.K.Rowling |
    Then the response of the status code should be 200
    And the response should include the updated book details:
      |id | title                         | author      |
      | 1 | Harry Potter - Goblet of Fire | J.K.Rowling |



