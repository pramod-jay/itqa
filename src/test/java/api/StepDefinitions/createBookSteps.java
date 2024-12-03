package api.StepDefinitions;

import api.models.BookDetails;
import api.utils.BaseUrlUtil;
import com.google.gson.Gson;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class createBookSteps {
    String baseUrl = BaseUrlUtil.BASE_URL;
    private Response response;
    private BookDetails bookDetails;

    @DataTableType
    public BookDetails mapToBookDetails(Map<String, String> row) {
        return new BookDetails(
                Integer.parseInt(row.get("id")),
                row.get("title"),
                row.get("author")
        );
    }

    @Given("the book details are:")
    public void the_book_details_are(DataTable dataTable) {
        List<BookDetails> bookDetailsList = dataTable.asList(BookDetails.class);
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
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
}
