package edu.salleurl.context.pokemon.information.domain.model.service;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.FavoriteCount;
import edu.salleurl.context.pokemon.information.domain.model.Id;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.ports.FavoritePokemonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes =FavoritePokemonSaver.class)
class FavoritePokemonSaverTest {

    @Autowired
    private FavoritePokemonSaver saver;

    @MockBean
    private FavoritePokemonRepository repository;

    @MockBean
    private PokemonFinder finder;

    @Test
    public void increaseTest() throws NotFoundException, RepositoryNotRespondingException {

        Pokemon pokemon = new Pokemon(new Id(1));
        pokemon.setFavoriteCount(new FavoriteCount());
        Mockito.when(finder.findOnlyFavorite("1")).thenReturn(pokemon);

        saver.Increase("1");
    }



}