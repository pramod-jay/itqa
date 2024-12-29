package api.StepDefinitions;

import api.models.BookDetails;
import api.utils.BaseUrlUtil;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class UpdateBookSteps {
    String baseUrl = BaseUrlUtil.BASE_URL;
    private Response response;
    private BookDetails bookDetails;
    List<BookDetails> bookDetailsList;

    @Severity(SeverityLevel.CRITICAL)
    @Step("Verify if a book exists in the system with ID {bookID}")
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

        if(response.getStatusCode() != 404) {
            bookDetails = response.getBody().as(BookDetails.class);
            System.out.println("Book with ID " + bookID + " exists: " + bookDetails);
        }
    }

    @Severity(SeverityLevel.NORMAL)
    @Step("Verify if a book exists in the system with ID {bookID}")
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

    @Severity(SeverityLevel.BLOCKER)
    @Step("Verify the response status code is {updateStatusCode}")
    @Then("the response of the status code should be {int}")
    public void the_response_of_the_status_code_should_be(Integer updateStatusCode) {
        Assert.assertEquals(updateStatusCode, response.getStatusCode());

    }

    @Severity(SeverityLevel.NORMAL)
    @Step("Validate the updated book details in the response")
    @Then("the response should include the updated book details:")
    public void the_response_should_include_the_updated_book_details(io.cucumber.datatable.DataTable resultTable) {
        //Convert the resultTable to a list of maps
        List<Map<String, String>> tableData = resultTable.asMaps(String.class, String.class);

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

    @Severity(SeverityLevel.MINOR)
    @Step("Verify the error message is {errorMsg}")
    @Then("the error message should be {string}")
    public void the_error_message_should_be(String errorMsg) {
        Assert.assertEquals(errorMsg, response.getBody().asString());
        System.out.println(response.getBody().prettyPrint());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("Send a PUT request as user {userName} to update book details")
    @When("I send a PUT request to {string} with following details with username as {string} with password {string}:")
    public void i_send_a_put_request_to_with_following_details_with_username_as_with_password(String endPoint, String userName, String password, io.cucumber.datatable.DataTable bookTable) {
        // Map the data table to a BookDetails object
        bookDetailsList = bookTable.asList(BookDetails.class);

        // Get the book details to update
        bookDetails = bookDetailsList.get(0);

        // Send the PUT request with the book details as JSON
        response = io.restassured.RestAssured.given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .auth().basic("user", "password")
                .body(bookDetails) // Automatically serializes the object to JSON
                .when()
                .put(baseUrl + endPoint + bookDetails.getId()); // Append the ID to the endpoint

        // Output the response for debugging
        System.out.println("Response: " + response.getBody().asString());
    }


}

