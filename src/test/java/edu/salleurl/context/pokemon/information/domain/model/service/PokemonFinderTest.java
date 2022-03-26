package edu.salleurl.context.pokemon.information.domain.model.service;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.ports.FavoritePokemonRepository;
import edu.salleurl.context.pokemon.information.domain.model.ports.PokemonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {PokemonFinder.class, FavoritePokemonRepository.class})
class PokemonFinderTest {

    @Autowired
    private PokemonFinder finder;

    @MockBean
    private PokemonRepository repository;

    @MockBean
    private FavoritePokemonRepository favoritePokemonRepository;

    @Test
    public void findTest() throws NotFoundException, RepositoryNotRespondingException {

        Pokemon pokemon = new Pokemon(1, "name", 1,1);

        Mockito.when(repository.getPokemonInformation(any())).thenReturn(Optional.of(pokemon));
        Pokemon pokemonResponse = finder.findComplete("10");

        Assertions.assertEquals(1, pokemonResponse.getId().getValue());
        Assertions.assertEquals("name", pokemonResponse.getName().getValue() );
        Assertions.assertEquals(1, pokemonResponse.getWeight().getValue() );
        Assertions.assertEquals(1, pokemonResponse.getHeight().getValue() );
    }

    @Test
    public void findTestRepositoryNotRespondingExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Mockito.when(repository.getPokemonInformation(any())).thenThrow(new RepositoryNotRespondingException(""));
        assertThrows(RepositoryNotRespondingException.class, () -> finder.findComplete("10"));
    }



}