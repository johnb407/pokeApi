package edu.salleurl.context.pokemon.user.application;

import edu.salleurl.context.pokemon.types.application.GetTypeUseCase;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.domain.service.UserAdderPokemon;
import edu.salleurl.context.pokemon.user.domain.service.UserFinder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {AddFavoriteUseCase.class})
class AddFavoriteUseCaseTest {

    @Autowired
    private AddFavoriteUseCase useCase;

    @MockBean
    private UserFinder finder;

    @MockBean
    private UserAdderPokemon adder;

    @Test
    public void AddFavoriteUseCaseTest() throws PokemonFavoriteExistsException, UserDontExistException {
        String id="id";
        String pokemonId = "pId";
        User user = new User(id, new HashSet<>());
        Mockito.when(finder.find(id)).thenReturn(user);
        User userAdd = new User(id, new HashSet<>());
        userAdd.addFavorite(pokemonId);
        Mockito.when(adder.add(user, pokemonId)).thenReturn(userAdd);
        useCase.execute(id,pokemonId);
    }

    @Test
    public void AddFavoriteUseCaseUserDontExistExceptionTest() throws PokemonFavoriteExistsException, UserDontExistException {
        String id="id";
        String pokemonId = "pId";
        Mockito.when(finder.find(id)).thenThrow(new UserDontExistException());
        assertThrows(UserDontExistException.class, () -> useCase.execute(id,pokemonId));
    }

    @Test
    public void AddFavoriteUseCasePokemonFavoriteExistsExceptionTest() throws PokemonFavoriteExistsException, UserDontExistException {
        String id="id";
        String pokemonId = "pId";
        User user = new User(id, new HashSet<>());
        Mockito.when(finder.find(id)).thenReturn(user);
        User userAdd = new User(id, new HashSet<>());
        userAdd.addFavorite(pokemonId);
        Mockito.when(adder.add(user, pokemonId)).thenThrow(new PokemonFavoriteExistsException(""));
        assertThrows(PokemonFavoriteExistsException.class, () -> useCase.execute(id,pokemonId));
    }



}