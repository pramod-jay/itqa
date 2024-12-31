package api.StepDefinitions;

import api.utils.BaseUrlUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Epic("Book API Tests")
@Feature("Get Book by ID")
@Owner("Buddima Dissanayake")
public class getBookByIdSteps {
    String baseUrl = BaseUrlUtil.BASE_URL;
    private Response response;
    int BookId;

    @Step("Set the book ID to {bookId}")
    @Description("Set the book ID for the test")
    @Given("the book id {int}")
    public void the_book_id(Integer bookId) {
        BookId = bookId;
    }
    @Step("Send a GET request to the {endpoint}")
    @Description("Send a GET request to fetch book details using the provided endpoint and book ID")
    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = RestAssured
                .given()
                .auth().basic("admin", "password")
                .header("Content-Type", "application/json")
                .get(baseUrl + endpoint + "/" + BookId)
                .then()
                .extract()
                .response();
        System.out.println(response.getBody().prettyPrint());
    }
    @Step("Validate that the response includes the referred book details")
    @Description("Validate the response body contains the expected book details")
    @Then("the response should include the referred book details:")
    public void the_response_should_include_the_book_details(DataTable dataTable) {
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        List<String> expectedValues = dataTable.asList(String.class);
        Set<String> actualValues = jsonPath.getMap("").values()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        Assert.assertEquals(actualValues, new HashSet<>(expectedValues));
    }

    @Step("Validate that the response status code is {statusCode}")
    @Description("Validate the response status code matches the expected value for a not found error")
    @Then("the not found response status code should be {int}")
    public void the_not_found_response_status_code_should_be(Integer statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
    @Step("Validate that the response contains the {message}")
    @Description("Validate the error message in the response body matches the expected value")
    @And("the not found response should include a message {string}")
    public void the_not_found_response_should_include_a_message(String message) {
        Assert.assertEquals(message, response.getBody().asString());
    }
}
