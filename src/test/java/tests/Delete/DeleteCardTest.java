package tests.Delete;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.util.Map;

public class DeleteCardTest extends BaseTest {

    private String createdCardId;

    @BeforeEach
    public void createCard() {
        Response response = requestWithAuth()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "name", "New Card",
                        "idList", UrlParamValues.EXISTING_LIST_ID
                ))
                .post(CardsEndpoints.CREATE_CARD_URL);
        response.then().statusCode(200);

        createdCardId = response.jsonPath().getString("id");
    }

    @Test
    public void checkDeleteCard() {

        requestWithAuth()
                .pathParam("id", createdCardId)
                .delete(CardsEndpoints.DELETE_CARD_URL)
                .then()
                .statusCode(200);

        requestWithAuth()
                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .body("id", Matchers.not(Matchers.hasItem(createdCardId)));
    }
}