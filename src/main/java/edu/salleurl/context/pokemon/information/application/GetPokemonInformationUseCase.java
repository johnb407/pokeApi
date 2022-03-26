package edu.salleurl.context.pokemon.information.application;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.service.PokemonFinder;
import org.springframework.stereotype.Service;

@Service
public class GetPokemonInformationUseCase {

    final private PokemonFinder finder;

    public GetPokemonInformationUseCase(PokemonFinder finder) {
        this.finder = finder;
    }

    public Pokemon execute(String pokemonId ) throws RepositoryNotRespondingException, NotFoundException {

        return finder.findComplete(pokemonId);
    }
}
