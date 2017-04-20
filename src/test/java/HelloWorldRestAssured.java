import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class HelloWorldRestAssured {

    @Test
    public void makeSureThatGoogleIsUp() {
        given().when().get("http://localhost:8080").then().statusCode(200);
    }

}
