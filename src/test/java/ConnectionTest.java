import com.epam.ws_socet.bean.Book;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItem;


public class ConnectionTest {
    public static Response response;
    public static String jsonAsString;
    private static Book book;

    @BeforeClass
    public void setUp() {
        book = new Book();
        book.setAuthor("Me");
        book.setDate("Today");
        book.setId(4);
        book.setLanguage("Python");
        book.setEdition("first");

        String port = System.getProperty("com.epam.ws_socet.bean.server.port");
        if (port == null) {
            RestAssured.port = 8080;
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("com.epam.ws_socet.bean.server.base");
        if(basePath==null){
            basePath = "/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("com.epam.ws_socet.bean.server.host");
        if(baseHost==null){
            baseHost = "http://localhost:8080";
        }
        RestAssured.baseURI = baseHost;

    }

    @Test(priority = 0)
    public void makeSureThatServerIsUp() {
        given().when().get("http://localhost:8080").then().statusCode(200);
    }

    @Test(priority = 1)
    public void exampleJsonPathTest() {

        response =
                when().
                        get("/get_all_books").
                        then().
                        contentType("application/json").  // check that the content type return from the API is JSON
                        extract().response(); // extract the response
    }

    @Test(priority = 2)
    public void isBookAdded() {
        given()
                .contentType("application/json")
                .body(book + "")
                .when()
                .post("/add_book")
                .then()
                .statusCode(201);
    }

    @Test(priority = 3)
    public void verifyNameOfBook() {
        given().when().get("/get_all_books").then()
                .body("books.language",hasItem(book.getLanguage()));
    }

    @Test(priority = 4)
    public void isBookDeleted() {

        given()
                .contentType("application/json")
                .body(book + "")
                .when()
                .post("/delete_book")
                .then()
                .statusCode(202);
    }

    @Test(priority = 5)
    public void isBookUpdated() {

        given()
                .contentType("application/json")
                .body(book + "")
                .when()
                .post("/update_book")
                .then()
                .statusCode(203);
    }

}
