package api.StepDefinitions;

import api.utils.BaseUrlUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class getBookByIdSteps {
    String baseUrl = BaseUrlUtil.BASE_URL;
    private Response response;
    int BookId;

    @Given("the valid book id {int}")
    public void the_valid_book_id(Integer bookId) {
        BookId = bookId;
    }
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
}
