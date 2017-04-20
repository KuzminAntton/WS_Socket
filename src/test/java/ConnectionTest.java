import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class ConnectionTest {

    @Test
    public void makeSureThatServerIsUp() {
        given().when().get("http://localhost:8080").then().statusCode(200);
    }

}
