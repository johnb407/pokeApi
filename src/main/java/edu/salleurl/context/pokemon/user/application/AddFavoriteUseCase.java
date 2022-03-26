package edu.salleurl.context.pokemon.user.application;

import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.domain.service.UserAdderPokemon;
import edu.salleurl.context.pokemon.user.domain.service.UserFinder;
import org.springframework.stereotype.Service;

@Service
public class AddFavoriteUseCase {

    private final UserFinder finder;

    private final UserAdderPokemon adder;

    public AddFavoriteUseCase(UserFinder finder, UserAdderPokemon adder) {
        this.finder = finder;
        this.adder = adder;
    }

    public void execute(String id, String pokemonId) throws UserDontExistException, PokemonFavoriteExistsException {
       User user = finder.find(id);
       adder.add(user, pokemonId);
    }
}
