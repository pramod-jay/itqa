package api.StepDefinitions;

import api.models.BookDetails;
import api.utils.BaseUrlUtil;
import com.google.gson.Gson;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class createBookSteps {
    String baseUrl = BaseUrlUtil.BASE_URL;
    private Response response;
    private BookDetails bookDetails;
    List<BookDetails> bookDetailsList;

    @DataTableType
    public BookDetails mapToBookDetails(Map<String, String> row) {
        return new BookDetails(
                row.get("id"),
                row.get("title"),
                row.get("author")
        );
    }

    @Given("the book details are:")
    public void the_book_details_are(DataTable dataTable) {
        bookDetailsList = dataTable.asList(BookDetails.class);
        bookDetails = bookDetailsList.get(0);
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String endpoint) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(bookDetails);
        response = RestAssured
                .given()
                .auth().basic("admin", "password")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(baseUrl + endpoint)
                .then()
                .extract()
                .response();
        System.out.println(response.getBody().prettyPrint());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should include the book details:")
    public void the_response_should_include_the_book_details(DataTable dataTable) {
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        List<String> expectedKeys = dataTable.asList(String.class);
        Set<String> actualKeys = jsonPath.getMap("").keySet()
                .stream()
                .map(Object::toString) // Convert each key to String
                .collect(Collectors.toSet());
        Assert.assertEquals(actualKeys, new HashSet<>(expectedKeys));
    }

    @When("I send a POST request to {string} with username {string} and password {string}")
    public void i_send_a_post_request_to_with_username_and_password(String endpoint, String username, String password) {
        Gson gson = new Gson();
        String requestBody = gson.toJson(bookDetails);
        response = RestAssured
                .given()
                .auth().basic(username, password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(baseUrl + endpoint)
                .then()
                .extract()
                .response();
        System.out.println(response.getBody().prettyPrint());
    }

    @And("the response should include an auto-generated id")
    public void the_response_should_include_an_auto_generated_id() {
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        Integer generatedId = jsonPath.get("id");

        // Check if the 'id' field is present
        Assert.assertTrue(jsonPath.get("id") != null, "Response does not contain an id field");

        // Check if the 'id' field has a valid value
        Assert.assertTrue(generatedId > 0, "Auto-generated id is not valid");
        System.out.println("Generated id: " + generatedId);
    }

    @And("the response should include a message {string}")
    public void the_response_should_include_a_message(String message) {
        Assert.assertEquals(message, response.getBody().asString());
    }
}