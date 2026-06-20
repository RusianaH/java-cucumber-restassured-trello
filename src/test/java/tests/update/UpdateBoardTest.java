package tests.update;

import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

public class UpdateBoardTest extends BaseTest {

    @Test
    public void checkUpdateBoard()
    {
        String updateName = "Updated Name" + LocalDateTime.now()
         .toString().replace(":", "").replace(".", "").replace("-", "").replace("T", "");

        requestWithAuth()
                .pathParam("id", UrlParamValues.BOARD_ID_TO_UPDATE)
                .body(Map.of("name", updateName))
                .contentType(ContentType.JSON)
                .put(BoardEndpoints.UPDATE_BOARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updateName));

        requestWithAuth()
                .pathParam("id", UrlParamValues.BOARD_ID_TO_UPDATE)
                .get(BoardEndpoints.GET_BOARD_URL)
                .then()
                .body("name", Matchers.equalTo(updateName));
    }}
