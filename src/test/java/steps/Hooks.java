package steps;

import consts.Endpoint;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.http.ContentType;
import utils.AuthReqProvider;

import static utils.AuthReqProvider.requestWithAuth;

public class Hooks {

    private final ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before("@createBoard")
    public void createBoard() {
        String boardId = requestWithAuth()
                .queryParam("name", "New board")
                .post(Endpoint.CREATE_A_BOARD.getUrl())
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().jsonPath().getString("id");
        scenarioContext.setBoardId(boardId);
    }


    @After("@deleteBoard")
    public void deleteBoard() {
        AuthReqProvider.requestWithAuth()
                .pathParam("id", scenarioContext.getBoardId())
                .delete(Endpoint.DELETE_A_BOARD.getUrl())
                .then().statusCode(200);
    }

    @Before("@createCard")
    public void createCard() {
        String cardId = requestWithAuth()
                .queryParam("name", "Card to Delete")
                .queryParam("idList", "6947fb85c9d5ca5b73207c60")
                .post(Endpoint.CREATE_CARD_URL.getUrl())
                .then().log().ifValidationFails()
                .statusCode(200)
                .extract().jsonPath().getString("id");
        scenarioContext.setCardId(cardId);
    }

    @After("@deleteCard")
    public void deleteCard() {
        requestWithAuth()
                .pathParam("id", scenarioContext.getCardId())
                .delete(Endpoint.DELETE_CARD_URL.getUrl())
                .then().statusCode(200);
    }

    @After
    public void attachResponse(Scenario scenario) {
        scenario.attach(scenarioContext.getResponse().asPrettyString(), "text/plain", "Response");
    }

}