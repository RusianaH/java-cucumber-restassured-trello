package tests.update;

import consts.BoardEndpoints;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

public class UpdateCardTest extends BaseTest {

    @Test
    public void checkUpdateCard()
    {
        String updateName = "Updated Name" + LocalDateTime.now()
         .toString().replace(":", "").replace(".", "").replace("-", "").replace("T", "");
        requestWithAuth()
                .pathParam("id", UrlParamValues.CARD_ID_TO_UPDATE)
                .body(Map.of("name", updateName))
                .contentType(ContentType.JSON)
                .put(CardsEndpoints.UPDATE_CARD_ID)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updateName));
        requestWithAuth()
                .pathParam("id", UrlParamValues.CARD_ID_TO_UPDATE)
                .get(CardsEndpoints.GET_CARD_URL)
                .then()
                .body("name", Matchers.equalTo(updateName));
    }}

