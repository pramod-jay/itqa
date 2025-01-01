Feature: getAllBooks



  Scenario: Successfully retrieve all books
    When I send a GET request to "api/books"
    Then the response should include a list of books:

      | id | title        | author      |
      | 1  | Harry Potter | J.K.Rowling |
      | 2  | Animal Farm | George Orwell |


