package edu.salleurl.context.pokemon.types.infraestructure.adapters.input.console;

import edu.salleurl.context.pokemon.types.application.GetTypeUseCase;
import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.stream.Collectors;

@Controller
@Qualifier("GetTypesController")
public class GetTypesController {

    final private GetTypeUseCase useCase;

    public GetTypesController(GetTypeUseCase useCase) {
        this.useCase = useCase;
    }

    public String GetTypes(String pokemonName) throws RepositoryNotRespondingException, NotFoundException {

        Types types = useCase.execute(pokemonName);
        final String response = types.getTypes().stream().map(x -> x.getType()).collect((Collectors.joining(",")));
        return response;

    }
}
