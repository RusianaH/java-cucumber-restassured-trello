package tests.get;

import consts.CardsEndpoints;
import consts.UrlParamValues;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

public class GetCardTest extends BaseTest {

    @Test
    public void checkGetCards() {
        requestWithAuth()
                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardsEndpoints.GET_ALL_CARDS_URL)
                .then()
                .statusCode(200);

    }
    @Test
    public void checkGetCard() {
        requestWithAuth()
                .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
                .get(CardsEndpoints.GET_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("jkt-bandung"));

    }
}
