# Author: Buddima Dissanayake
# Date 31/12/2024
# Description: Feature file for test get book by id API

Feature: get book by id

  @TestCaseId("API_TC015")
  Scenario: Successfully fetching a book by valid id
    Given the book id 1
    When I send a GET request to "api/books"
    Then the response should include the referred book details:

      | id | title        | author      |
      | 1  | Harry Potter | J.K.Rowling |

  @TestCaseId("API_TC016")
  Scenario: Book is not found when fetching by invalid id
    Given the book id 7
    When I send a GET request to "api/books"
    Then the not found response status code should be 404
    And the not found response should include a message "Book not found"

