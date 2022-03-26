package edu.salleurl.context.pokemon.types.application;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Name;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.domain.model.service.TypesFinder;
import org.springframework.stereotype.Service;

@Service
public class GetTypeUseCase {

    final private TypesFinder finder;

    public GetTypeUseCase(TypesFinder finder) {
        this.finder = finder;
    }

    public Types execute(String pokemonName ) throws RepositoryNotRespondingException, NotFoundException {
        Name name = new Name(pokemonName);
        return finder.find(name);
    }
}
