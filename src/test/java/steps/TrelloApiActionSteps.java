package steps;

import consts.Endpoint;
import consts.UrlParamValues;
import io.cucumber.core.options.CurlOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.AuthReqProvider;

public class TrelloApiActionSteps {

    private final ScenarioContext scenarioContext;

    public TrelloApiActionSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    private RequestSpecification requestWithAuth() {
        return requestWithoutAuth()
                .queryParams(UrlParamValues.AUTH_QUERY_PARAMS);
    }

    private RequestSpecification requestWithoutAuth() {
        RestAssured.baseURI = "https://api.trello.com";
        return RestAssured.given();
    }


    @Given("a request {with} authorization")
    public void aRequestWithAuthorization(boolean withAuth) {
        scenarioContext.setRequest(withAuth ? AuthReqProvider.requestWithAuth() : AuthReqProvider.requestWithoutAuth());
    }

    @And("the request has query params:")
    public void theRequestHasQueryParam(Map<String, String> queryParams) {
        scenarioContext.setRequest(scenarioContext.getRequest().queryParams(queryParams));
    }

    @And("the request has body params:")
    public void theRequestHasBodyParam (Map<String, String> queryParams){
        scenarioContext.setRequest(scenarioContext.getRequest().queryParams(queryParams));
    }

    @And("the request has headers:")
    public void theRequestHasHeader(DataTable dataTable) {
        scenarioContext.setRequest(scenarioContext.getRequest().headers(dataTable.asMap()));
    }

    @And("the request has path params:")
    public void theRequestHasPathParam(DataTable dataTable) {
        Map<String, String> pathParams = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            String rowValue = row.get("value");
            String value = rowValue.equals("created_board_id") ? scenarioContext.getBoardId() : rowValue;
            pathParams.put(row.get("name"), value);
        }
        scenarioContext.setRequest(scenarioContext.getRequest().pathParams(pathParams));
    }



    @When("the '{}' request is sent to '{endpoint}' endpoint")
    public void theRequestIsSentToEndpoint(CurlOption.HttpMethod method, Endpoint endpoint) {
        String url = endpoint.getUrl();
        switch (method) {
            case GET -> scenarioContext.setResponse(scenarioContext.getRequest().get(url));
            case PUT -> scenarioContext.setResponse(scenarioContext.getRequest().put(url));
            case POST -> scenarioContext.setResponse(scenarioContext.getRequest().post(url));
            case DELETE -> scenarioContext.setResponse(scenarioContext.getRequest().delete(url));
            default -> throw new RuntimeException();
        }
    }

    @Given("a request with {string} auth condition")
    public void aRequestWithAuthCondition(String authCondition) {
        RequestSpecification request = switch (authCondition) {
            case "no_auth"      -> requestWithoutAuth();
            case "only_key"     -> requestWithoutAuth().queryParams(UrlParamValues.ONLY_KEY);
            case "only_token"   -> requestWithoutAuth().queryParams(UrlParamValues.ONLY_TOKEN);
            case "another_user" -> requestWithoutAuth().queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS);
            default -> throw new RuntimeException("Auth condition tidak dikenali: " + authCondition);
        };
        scenarioContext.setRequest(request);
    }
    @Given("a new board is created")
    public void aNewBoardIsCreated() {
        requestWithAuth()
                .queryParam("name", "New Board")
                .post(Endpoint.CREATE_A_BOARD.getUrl());
        scenarioContext.setRequest(requestWithAuth());
    }
    @And("the response body contains board name {string}")
    public void theResponseBodyContainsBoardName(String boardName) {
        List<String> boardNames = scenarioContext.getResponse().jsonPath().getList("name", String.class);
        MatcherAssert.assertThat(boardNames, Matchers.hasItem(boardName));
    }
    @When("the board ID from the response is remembered")
    public void theBoardIdFromTheResponseIsRemembered() {
        String createdBoardId = scenarioContext.getResponse().body()
                .jsonPath().getString("id");
        scenarioContext.setBoardId(createdBoardId);
}
//    @When("the cards ID from the response is remembered")
//    public void theBoardIdFromTheResponseIsRemembered() {
//        String createdBoardId = scenarioContext.getResponse().body()
//                .jsonPath().getString("id");
//        scenarioContext.setBoardId(createdBoardId);
//    }
};