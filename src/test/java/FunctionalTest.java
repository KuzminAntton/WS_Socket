import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;

public class FunctionalTest {
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("com.epam.ws_socet.bean.server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("com.epam.ws_socet.bean.server.base");
        if(basePath==null){
            basePath = "/rest-garage-sample/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("com.epam.ws_socet.bean.server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }
}
