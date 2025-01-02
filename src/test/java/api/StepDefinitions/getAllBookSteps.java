package api.StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import api.utils.BaseUrlUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import io.qameta.allure.*;
import java.util.List;
import java.util.Map;



@Epic("Book API Tests")
@Feature("Get Book")
@Owner("Lasantha Pradeep")
public class getAllBookSteps {


    Response response;
    String baseUrl = BaseUrlUtil.BASE_URL;


    @Severity(SeverityLevel.CRITICAL)
    @Description("Send a GET request to fetch all books from the API endpoint")
    @Step("Send GET request to endpoint {0}")
    @When("I send GET request to {string}")
    public void i_send_get_request_to(String  endpoint ) {
         response = RestAssured
                .given()
                .auth().basic("admin", "password")
                .header("Content-Type", "application/json")
                .get(baseUrl + endpoint)
                .then()
                .extract()
                .response();
        System.out.println(response.getBody().prettyPrint());
    }


    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the response contains a list of expected books")
    @Step("Verify response contains expected book list")
    @Then("the response should include a list of books:")
    public void the_response_should_include_the_book_details(DataTable dataTable) {
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        // Extract expected values from the DataTable
        List<List<String>> expectedBooks = dataTable.asLists(String.class);

        // Iterate through expected books and verify they exist in the response
        for (List<String> expectedBook : expectedBooks.subList(1, expectedBooks.size())) { // Skip the header row
            String expectedId = expectedBook.get(0);
            String expectedTitle = expectedBook.get(1);
            String expectedAuthor = expectedBook.get(2);

            // Find the book in the response
            List<Map<String, Object>> books = jsonPath.getList(""); // Assumes the response is an array of books
            boolean bookExists = books.stream().anyMatch(book ->
                    book.get("id").toString().equals(expectedId) &&
                            book.get("title").equals(expectedTitle) &&
                            book.get("author").equals(expectedAuthor)
            );

            Assert.assertTrue(bookExists, "Book not found: " + expectedBook);
        }
    }
}


