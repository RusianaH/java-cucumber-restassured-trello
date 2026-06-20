package arguments.providers;

import arguments.Holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class BoardAuthValidationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "unauthorized permission requested"
                )),

                Arguments.of(new AuthValidationArgumentsHolder(
                        Map.of("key", UrlParamValues.VALID_KEY),
                        "missing scopes"  //
                )),

                Arguments.of(new AuthValidationArgumentsHolder(
                        Map.of("token", UrlParamValues.VALID_TOKEN),
                        "invalid key"


        ))
        );
    }
}
