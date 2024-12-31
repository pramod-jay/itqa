# Author: Buddima Dissanayake
# Date 31/12/2024
# Description: Feature file for test get book by id API

Feature: get book by id

  Scenario: Successfully fetching a book by valid id
    Given the valid book id 1
    When I send a GET request to "api/books"
    Then the response should include the referred book details:
      | id | title        | author      |
      | 1  | Harry Potter | J.K.Rowling |
