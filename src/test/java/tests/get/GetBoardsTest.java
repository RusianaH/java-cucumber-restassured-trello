package tests.get;

import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class GetBoardsTest extends BaseTest {


    @Test
    public void checkGetBoards() {
        requestWithAuth()
                .queryParams("fields", "id,name")
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardEndpoints.GET_ALL_BOARDS_URL)
                .then().log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));

    }
    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoints.GET_BOARD_URL)
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"))
                .body("name", Matchers.equalTo("itenerary"));
    }
}
