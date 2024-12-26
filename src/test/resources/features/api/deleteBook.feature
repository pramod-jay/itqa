# Author Erandi Malshika
# Date 26/12/2024
# Description Feature file for test delete book API

Feature: DeleteBook
  As an API tester
  I want to test the DELETE Book API functionality for various scenarios
  So that I can verify access control, functionality, and error handling

  Background:
    Given I am logged in as "admin"

  @DeleteTest
  @TestCaseId("API_TC007")
  Scenario: Successfully delete a book with a valid ID
    Given I am logged in as "admin"
    When I send a DELETE request to "api/books/1"
    Then the delete response status code should be 200
    And the delete response should include a message "Book deleted successfully"

  @DeleteTest
  @TestCaseId("API_TC008")
  Scenario: Attempt to delete a book that does not exist
    When I send a DELETE request to "api/books/9999"
    Then the delete response status code should be 404
    And the delete response should include a message "Book not found"

  @DeleteTest
  @TestCaseId("API_TC009")
  Scenario: Unsuccessfully delete a book without proper authorization
    When I send a DELETE request to "api/books/2" with username "testUser" and password "wrongPassword"
    Then the delete response status code should be 401
    And the delete response should include a message "Unauthorized access"

  @DeleteTest
  @TestCaseId("API_TC010")
  Scenario: Unsuccessfully delete a book with invalid ID format
    When I send a DELETE request to "api/books/abc"
    Then the delete response status code should be 400
    And the delete response should include a message "Invalid ID format"

  @DeleteTest
  @TestCaseId("API_TC011")
  Scenario Outline: Unsuccessfully delete a book due to missing book ID
    Given I have the base API endpoint "api/books/"
    When I send a DELETE request to "<endpoint>"
    Then the delete response status code should be 404
    And the delete response should include a message "Book ID is missing"

    Examples:
      | endpoint   |
      | api/books/ |

  @DeleteTest
  @TestCaseId("API_TC012")
  Scenario: Admin deletes an existing book
    Given I am logged in as "admin"
    When I send a DELETE request to "/api/books/2"
    Then the delete response status code should be 200
    And the delete response should include a message "Book deleted successfully"

  @DeleteTest
  @TestCaseId("API_TC0013")
  Scenario: User tries to delete an existing book
    Given I am logged in as "user"
    When I send a DELETE request to "/api/books/3"
    Then the delete response status code should be 403
    And the delete response should include a message "Forbidden access"
