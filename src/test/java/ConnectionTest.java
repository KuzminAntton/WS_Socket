import bean.Book;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class ConnectionTest {
    public static Response response;
    public static String jsonAsString;
    @Test
    public void makeSureThatServerIsUp() {
        given().when().get("http://localhost:8080").then().statusCode(200);
    }

    @Test
    public void exampleJsonPathTest() {

        response =
                when().
                        get("/").
                        then().
                        contentType("text/html").  // check that the content type return from the API is JSON
                        extract().response(); // extract the response
    }

    @Test
    public void verifyNameOfGarage() {
        ArrayList<String> listOfEditions = given().when().get("/get_all_books").path("books.edition");
        System.out.println(listOfEditions.get(0));
        given().when().get("/get_all_books").then()
                .body("books",equalTo("Java"));
    }



    @Test
    public void aCarObjectGoesIntoTheGarage() {
        Book book = new Book();
            book.setAuthor("Me");
            book.setDate("Today");
            book.setId(4);
            book.setLanguage("Python");
            book.setEdition("first");

        given()
                .contentType("application/json")
                .body(book)
                .when().post("/add_book").then();
    }

}
