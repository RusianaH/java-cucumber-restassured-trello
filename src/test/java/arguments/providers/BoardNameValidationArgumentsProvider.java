package arguments.providers;

import io.restassured.specification.Argument;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class BoardNameValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext Context){
        return Stream.of(
                Map.of("name", 12345),
                Collections.emptyMap()
        ).map(Arguments::of);
    }
}
