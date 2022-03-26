package edu.salleurl.context.pokemon.information.application;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.service.FavoritePokemonSaver;
import edu.salleurl.context.pokemon.information.domain.model.service.PokemonFinder;
import org.springframework.stereotype.Service;

@Service
public class IncreaseFavoritePokemonUseCase {

    final private FavoritePokemonSaver saver;

    public IncreaseFavoritePokemonUseCase( FavoritePokemonSaver saver) {
        this.saver = saver;
    }

    public void execute(String pokemonId ) throws RepositoryNotRespondingException, NotFoundException {
        saver.Increase(pokemonId);
    }
}
