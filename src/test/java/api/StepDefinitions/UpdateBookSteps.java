package api.StepDefinitions;

import api.models.BookDetails;
import api.utils.BaseUrlUtil;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class UpdateBookSteps {
    String baseUrl = BaseUrlUtil.BASE_URL;
    private Response response;
    private BookDetails bookDetails;
    List<BookDetails> bookDetailsList;

    @Given("a book exist in the system with ID {int}")
    public void a_book_exist_in_the_system_with_id(Integer bookID) {
        response = io.restassured.RestAssured.given()
                .baseUri(baseUrl)
                .auth().basic("admin", "password")
                .when()
                .get(baseUrl + "api/books/" + bookID)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() == 404) {
            throw new AssertionError("Book with ID " + bookID + " does not exist in the system.");
        }

//        System.out.println(response.getBody().prettyPrint());
        bookDetails = response.getBody().as(BookDetails.class);
        System.out.println("Book with ID " + bookID + " exists: " + bookDetails);
    }
    @When("I send a PUT request to {string} with following details:")
    public void i_send_a_put_request_to_with_following_details(String endPoint, io.cucumber.datatable.DataTable bookTable) {
        // Map the data table to a BookDetails object
        bookDetailsList = bookTable.asList(BookDetails.class);

        // Get the book details to update
        bookDetails = bookDetailsList.get(0);

        // Send the PUT request with the book details as JSON
        response = io.restassured.RestAssured.given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .auth().basic("admin", "password")
                .body(bookDetails) // Automatically serializes the object to JSON
                .when()
                .put(baseUrl + endPoint + bookDetails.getId()); // Append the ID to the endpoint

        // Output the response for debugging
        System.out.println("Response: " + response.getBody().asString());

    }
    @Then("the response of the status code should be {int}")
    public void the_response_of_the_status_code_should_be(Integer updateStatusCode) {
        Assert.assertEquals(updateStatusCode, response.getStatusCode());

    }
    @Then("the response should include the updated book details:")
    public void the_response_should_include_the_updated_book_details(io.cucumber.datatable.DataTable resultTable) {
        //Convert the resultTable to a list of maps
        List<Map<String, String>> tableData = resultTable.asMaps(String.class, String.class);

        // Validate there's only one row in the result table
        if (tableData.size() != 1) {
            throw new IllegalArgumentException("This step expects exactly one set of book details to be provided.");
        }

        // Extract expected details from the table
        Map<String, String> expectedDetails = tableData.get(0);
        BookDetails expectedBookDetails = new BookDetails(
                expectedDetails.get("id"),
                expectedDetails.get("title"),
                expectedDetails.get("author")
        );

        // Deserialize the response body into a BookDetails object
        BookDetails updatedBookDetails = response.getBody().as(BookDetails.class);

        // Perform assertions to validate the response
        Assert.assertEquals(updatedBookDetails.getId(), expectedBookDetails.getId(), "Book ID mismatch");
        Assert.assertEquals(updatedBookDetails.getTitle(), expectedBookDetails.getTitle(), "Book title mismatch");
        Assert.assertEquals(updatedBookDetails.getAuthor(), expectedBookDetails.getAuthor(), "Book author mismatch");

        // Print the actual book details for debugging
        System.out.println("Updated Book Details: " + updatedBookDetails);
    }

//    @Given("no book exists in the system with ID {int}")
//    public void no_book_exists_in_the_system_with_id(Integer invalidBookID) {
//        response = io.restassured.RestAssured.given()
//                .baseUri(baseUrl)
//                .auth().basic("admin", "password")
//                .when()
//                .get(baseUrl + "api/books/" + invalidBookID)
//                .then()
//                .extract()
//                .response();
//
//        // Assuming it should return 404 if the book doesn't exist
//        Assert.assertEquals(404, response.getStatusCode());
//    }

    @Then("the error message should be {string}")
    public void the_error_message_should_be(String errorMsg) {
        Assert.assertEquals(errorMsg, response.getBody().asString());
    }

}

