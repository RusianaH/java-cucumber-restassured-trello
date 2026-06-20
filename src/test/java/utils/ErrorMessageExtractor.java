package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ErrorMessageExtractor {

    public static String getMessage(Response response) {
        try {
            JsonPath json = response.jsonPath();
            String message = json.getString("message");
            if (message != null) {
                return message;
            }
            return response.getBody().asString();
        } catch (Exception e) {
            return response.getBody().asString();
        }
    }
}