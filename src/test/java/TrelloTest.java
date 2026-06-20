import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TrelloTest {

    @BeforeAll
    public static void setBaseURL(){
        RestAssured.baseURI ="https://google.com";
    }

    @Test
    public void checkgoogleHome(){
        RestAssured.given()
                .log().all()
                .get()
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
