package tests.Delete;

import arguments.Holders.AuthValidationArgumentsHolder;
import arguments.Holders.BoardIdValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.BoardEndpoints;
import consts.CardsEndpoints;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;
import utils.ErrorMessageExtractor;

public class DeleteBoardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidid(BoardIdValidationArgumentsHolder argumentsHolder){
        Response response = requestWithAuth()
                .pathParams(argumentsHolder.getPathParams())
                .delete(BoardEndpoints.DELETE_BOARD_URL);
        response
                .then()
                .statusCode(argumentsHolder.getStatusCode());
        Assertions.assertEquals(argumentsHolder.getErrorMessage(),response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .delete(BoardEndpoints.DELETE_BOARD_URL);
        response
                .then().log().all()
                .statusCode(401);
        String message = ErrorMessageExtractor.getMessage(response);
        Assertions.assertEquals(validationArguments.getErrorMessage(), message);
    }
}
