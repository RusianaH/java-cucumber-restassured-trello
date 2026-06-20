package tests.Delete;
import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.util.Map;

public class DeleteBoardTest extends BaseTest {

    private String createdBoardId;

    @BeforeEach
    public void createBoard() {
        createdBoardId = requestWithAuth()
                .body(Map.of("name", "New Board"))
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_BOARD_URL)
                .body().jsonPath().get("id");
    }

    @Test
    public void checkDeleteBoard() {
        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete(BoardEndpoints.DELETE_BOARD_URL)
                .then()
                .statusCode(200)
                        .body("_value", Matchers.equalTo(null));

        requestWithAuth()
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardEndpoints.GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.hasItem(createdBoardId)));
    }
}
