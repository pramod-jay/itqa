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

  Scenario: Fail to update a book with invalid ID
    Given a book exist in the system with ID 1900
    When I send a PUT request to "api/books/" with following details:
      |id    | title          | author      |
      |1900  | Les Miserables | Victor Hugo |
    Then the response of the status code should be 404
    And the error message should be "Book not found"

  Scenario: Fail to update a book with missing mandatory parameters
      Given a book exist in the system with ID 1
      When I send a PUT request to "api/books/" with following details:
        | id    | title | author |
        | 1     |       |        |
      Then the response of the status code should be 400
      And the error message should be "Invalid | Empty Input Parameters in the Request"
#    And the error message should be "Mandatory parameters should not be null"


  Scenario: Unauthorized attempt to update a book
    Given a book exist in the system with ID 1
    When I send a PUT request to "api/books/" with following details with username as "user" with password "password":
      | id    | title                          | author     |
      | 1     | Adventures of Huckleberry Finn | Mark Twain |
    Then the response of the status code should be 403
    And the error message should be "User is not permitted."


