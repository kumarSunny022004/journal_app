package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Users;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ArgumentSourceProvider implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(Users.builder().username("Shyam").password("Shyam").build()),
                Arguments.of(Users.builder().username("Suraj").password("").build())

        );
    }
}
