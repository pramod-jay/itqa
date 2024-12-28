package api.StepDefinitions;

import api.utils.ConfigUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Epic("Book API Testing")
@Feature("Delete Book API")
@Owner("Erandi Malshika")
public class DeleteBookSteps {
    private final String baseUrl = ConfigUtil.get("base.url");
    private String username;
    private String password;
    private Response response;
    private final List<Integer> createdBookIds = new ArrayList<>(); // Track created books for cleanup
    private int createdBookId;

    @Before("@DeleteTest")
    @Step("Setting up data for Delete API tests")
    public void setup() {
        System.out.println("Setting up data for Delete API tests...");
        String adminUsername = ConfigUtil.get("admin.username");
        String adminPassword = ConfigUtil.get("admin.password");

        String dynamicBookTitle = "Test Book " + System.currentTimeMillis();
        String requestBody = String.format("{ \"title\": \"%s\", \"author\": \"Dynamic Author\" }", dynamicBookTitle);
        Response createResponse = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(adminUsername, adminPassword)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseUrl + "api/books")
                .then()
                .extract()
                .response();

        if (createResponse.getStatusCode() == 201) {
            createdBookId = createResponse.jsonPath().getInt("id");
            createdBookIds.add(createdBookId); // Track the created book ID
            System.out.println("Created book for testing: ID " + createdBookId);
            System.out.println(createdBookIds);
        } else {
            System.err.println("Failed to create test book. Status code: " + createResponse.getStatusCode());
        }
    }

    @After("@DeleteTest")
    @Step("Cleaning up data for Delete API tests")
    public void teardown() {
        System.out.println("Cleaning up data for Delete API tests...");
        String adminUsername = ConfigUtil.get("user.username");
        String adminPassword = ConfigUtil.get("user.password");

        for (int bookId : createdBookIds) {
            RestAssured
                    .given()
                    .auth()
                    .preemptive()
                    .basic(adminUsername, adminPassword)
                    .delete(baseUrl + "/api/books/" + bookId);
            System.out.println("Deleted test book: ID " + bookId);
        }
        createdBookIds.clear(); // Clear the list after cleanup
        System.out.println("Cleanup complete.");
    }

    @Given("I am logged in as {string}")
    @Step("Log in as user role: {role}")
    public void iAmLoggedInAs(String role) {
        if (role.equalsIgnoreCase("admin")) {
            username = ConfigUtil.get("admin.username");
            password = ConfigUtil.get("admin.password");
        } else if (role.equalsIgnoreCase("user")) {
            username = ConfigUtil.get("user.username");
            password = ConfigUtil.get("user.password");
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    @Given("the delete book details are:")
    @Step("Providing delete book details: {bookDetails}")
    public void theBookDetailsAre(Map<String, String> bookDetails) {
        String requestBody = String.format(
                "{\"id\": \"%s\", \"title\": \"%s\", \"author\": \"%s\"}",
                bookDetails.get("id"),
                bookDetails.get("title"),
                bookDetails.get("author")
        );
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(baseUrl + "/api/books");
    }

    @When("I send a DELETE request to {string}")
    @Step("Sending DELETE request to endpoint: {endpoint}")
    public void iSendADeleteRequestTo(String endpoint) {
        System.out.println(username);
        this.response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(username, password)
                .delete(baseUrl + endpoint);
    }

    @When("I send a DELETE request to {string} with username {string} and password {string}")
    @Step("Sending DELETE request to endpoint: {endpoint} with username: {username}")
    public void iSendADeleteRequestToWithCredentials(String endpoint, String username, String password) {
        this.response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(username, password)
                .delete(baseUrl + endpoint);
    }

    @Then("the delete response status code should be {int}")
    @Step("Verify response status code is {expectedStatusCode}")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that the response status code matches the expected value")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @When("I send a DELETE request to the dynamically created book")
    @Step("Sending DELETE request to dynamically created book with ID: {createdBookId}")
    public void iSendADeleteRequestToDynamicallyCreatedBook() {
        response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic(username, password)
                .delete(baseUrl + "api/books/" + createdBookId);

        System.out.println("Sent DELETE request for book ID: " + createdBookId);
    }
}
