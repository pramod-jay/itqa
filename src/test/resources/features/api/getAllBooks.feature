Feature: getAllBooks



  @TestCaseId("API_TC016")
  Scenario: Successfully retrieve all books
    When I send GET request to "api/books"
    Then the response should include a list of books:

      | id | title        | author      |
      | 1  | Harry Potter | J.K.Rowling |
      | 2  | Animal Farm | George Orwell |


