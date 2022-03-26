package edu.salleurl.context.pokemon.user.domain.service;

import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.interfaces.FavoritePokemonPublisher;
import edu.salleurl.context.pokemon.user.domain.interfaces.UserRespository;
import edu.salleurl.context.pokemon.user.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserAdderPokemon {

    private final UserRespository repository;

    private final FavoritePokemonPublisher publisher;

    public UserAdderPokemon(UserRespository repository, FavoritePokemonPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public User add(User user, String pokemon) throws PokemonFavoriteExistsException {
        user.addFavorite(pokemon);
        repository.save(user);
        publisher.publish(pokemon);

        return user;
    }
}
