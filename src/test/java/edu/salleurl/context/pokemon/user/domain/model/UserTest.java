package edu.salleurl.context.pokemon.user.domain.model;

import edu.salleurl.context.pokemon.user.application.AddFavoriteUseCase;
import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void  duplicateFavoritePokemonTest() throws PokemonFavoriteExistsException {
        User user = new User();
        user.addFavorite("pId");
        assertThrows(PokemonFavoriteExistsException.class, () -> user.addFavorite("pId"));
    }

    @Test
    public void addingPokemonTest() throws PokemonFavoriteExistsException {
        User user = new User();
        user.addFavorite("pId");
        user.addFavorite("pId2");
        Assertions.assertEquals(user.getIdFavorites().size(),2);
    }


}