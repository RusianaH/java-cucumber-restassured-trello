package tests.get;

import arguments.Holders.AuthValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardAuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import arguments.Holders.BoardIdValidationArgumentsHolder;
import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import utils.ErrorMessageExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;

public class GetBoardsValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidId(BoardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());

    }

    @ParameterizedTest
    @ArgumentsSource(BoardAuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(
                validationArguments.getErrorMessage(),
                response.body().asString());
//        Assertions.assertEquals("missing scope", response.body().asString());
    }

    @Test
    public void checkGetBoardWithAnotherUserCredentials() {
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoints.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }
}