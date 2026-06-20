package arguments.providers;

import arguments.Holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class CardAuthValidationArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {

        return Stream.of(

                // no key & no token
                Arguments.of(new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "unauthorized card permission requested"
                )),

                // only key
                Arguments.of(new AuthValidationArgumentsHolder(
                        Map.of("key", UrlParamValues.VALID_KEY),
                        "missing scopes"
                )),

                // only token
                Arguments.of(new AuthValidationArgumentsHolder(
                        Map.of("token", UrlParamValues.VALID_TOKEN),
                        "invalid key"
                ))
        );
    }
}