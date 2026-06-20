package tests.create;

import arguments.Holders.AuthValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.providers.BoardIdValidationArgumentsProvider;
import arguments.providers.BoardNameValidationArgumentsProvider;
import consts.BoardEndpoints;
import consts.UrlParamValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tests.BaseTest;
import utils.ErrorMessageExtractor;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(BoardNameValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidName(Map bodyParams) {
        Response response = requestWithAuth()
                .body(bodyParams)
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_BOARD_URL);
        response
                .then()
                .statusCode(400);
//        Assertions.assertEquals("invalid value for name", response.body().asString());
        String message = ErrorMessageExtractor.getMessage(response);
        Assertions.assertEquals("invalid value for name", message);
    }
    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments) {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .body(Map.of("name", "new board"))
                .contentType(ContentType.JSON)
                .post(BoardEndpoints.CREATE_BOARD_URL);

        response
                .then()
                .statusCode(401);
//        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
        String message = ErrorMessageExtractor.getMessage(response);
        Assertions.assertEquals(validationArguments.getErrorMessage(), message);

    }
}
