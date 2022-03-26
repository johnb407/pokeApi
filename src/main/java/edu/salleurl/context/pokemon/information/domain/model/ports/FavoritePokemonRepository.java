package edu.salleurl.context.pokemon.information.domain.model.ports;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.FavoriteCount;
import edu.salleurl.context.pokemon.information.domain.model.Id;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;

import java.util.Optional;

public interface FavoritePokemonRepository {

    FavoriteCount getPokemonFavoriteCount(Id pokemonId) throws RepositoryNotRespondingException, NotFoundException;

    void save(edu.salleurl.context.pokemon.information.domain.model.Pokemon pokemon) throws RepositoryNotRespondingException, NotFoundException;
}
