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
  Scenario: Admin deletes an existing book
    Given I am logged in as "admin"
    When I send a DELETE request to the dynamically created book
    Then the delete response status code should be 200

  @DeleteTest
  @TestCaseId("API_TC0008")
  Scenario: User tries to delete an existing book
    Given I am logged in as "user"
    When I send a DELETE request to the dynamically created book
    Then the delete response status code should be 403

  @DeleteTest
  @TestCaseId("API_TC009")
  Scenario: Successfully delete a book with a valid ID
    When I send a DELETE request to the dynamically created book
    Then the delete response status code should be 200

  @DeleteTest
  @TestCaseId("API_TC010")
  Scenario: Attempt to delete a book that does not exist
    When I send a DELETE request to "api/books/9999"
    Then the delete response status code should be 404

  @DeleteTest
  @TestCaseId("API_TC011")
  Scenario: Unsuccessfully delete a book without proper authorization
    When I send a DELETE request to "api/books/2" with username "testUser" and password "wrongPassword"
    Then the delete response status code should be 401

  @DeleteTest
  @TestCaseId("API_TC012")
  Scenario: Unsuccessfully delete a book with invalid ID format
    When I send a DELETE request to "api/books/abc"
    Then the delete response status code should be 400

  @DeleteTest
  @TestCaseId("API_TC013")
  Scenario: Unsuccessfully delete a book due to missing book ID
    When I send a DELETE request to "api/books/"
    Then the delete response status code should be 405

