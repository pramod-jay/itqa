package api.StepDefinitions;

import api.utils.BaseUrlUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

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
}
