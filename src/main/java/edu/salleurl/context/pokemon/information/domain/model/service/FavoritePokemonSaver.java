package edu.salleurl.context.pokemon.information.domain.model.service;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.ports.FavoritePokemonRepository;
import org.springframework.stereotype.Component;

@Component
public class FavoritePokemonSaver {


    private final FavoritePokemonRepository repository;

    private final PokemonFinder finder;

    public FavoritePokemonSaver(FavoritePokemonRepository repository, PokemonFinder finder) {
        this.repository = repository;
        this.finder = finder;
    }

    public void Increase(String pokemonID) throws RepositoryNotRespondingException, NotFoundException {
        Pokemon onlyFavorite = finder.findOnlyFavorite(pokemonID);
        onlyFavorite.increaseFavoriteCount();
        repository.save(onlyFavorite);
    }
}
