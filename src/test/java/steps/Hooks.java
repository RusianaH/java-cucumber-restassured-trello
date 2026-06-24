package steps;

import consts.Endpoint;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

}