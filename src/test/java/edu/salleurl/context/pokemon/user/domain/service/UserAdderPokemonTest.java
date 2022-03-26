package edu.salleurl.context.pokemon.user.domain.service;

import edu.salleurl.context.pokemon.user.application.AddFavoriteUseCase;
import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.interfaces.FavoritePokemonPublisher;
import edu.salleurl.context.pokemon.user.domain.interfaces.UserRespository;
import edu.salleurl.context.pokemon.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {UserAdderPokemon.class})
class UserAdderPokemonTest {

    @Autowired
    private UserAdderPokemon adder;

    @MockBean
    private UserRespository repository;

    @MockBean
    private FavoritePokemonPublisher publisher;

    @Test
    public void addPokemonTest() throws PokemonFavoriteExistsException {
        User user = new User();
        adder.add(user, "pokemon");
    }

    @Test
    public void addPokemonPokemonFavoriteExistsExceptionTest() throws PokemonFavoriteExistsException {
        User user = new User();
        user.addFavorite("pokemon");
        assertThrows(PokemonFavoriteExistsException.class, () -> adder.add(user, "pokemon"));
    }

}