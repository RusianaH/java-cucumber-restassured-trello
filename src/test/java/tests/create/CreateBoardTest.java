package tests.create;

import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard() {
        String boardName = "New Board" + LocalDateTime.now()
        .toString()
                .replace(":", "")
                .replace(".", "")
                .replace("-", "")
                .replace("T", "");

        Response response = requestWithAuth()
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_BOARD_URL);
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
        createdBoardId = response.body().jsonPath().get("id");

        requestWithAuth()
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardEndpoints.GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.hasItem(boardName));

    }

    @AfterEach
    public void deleteCreatedBoard(){
        if (createdBoardId != null) {
            requestWithAuth()
                    .pathParam("id", createdBoardId)
                    .delete(BoardEndpoints.DELETE_BOARD_URL)
                    .then()
                    .statusCode(200);
        }

    }}

