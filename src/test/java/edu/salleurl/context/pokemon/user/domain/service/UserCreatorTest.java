package edu.salleurl.context.pokemon.user.domain.service;

import edu.salleurl.context.pokemon.user.application.AddFavoriteUseCase;
import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import edu.salleurl.context.pokemon.user.domain.interfaces.UserRespository;
import edu.salleurl.context.pokemon.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {UserCreator.class})
class UserCreatorTest {

    @Autowired
    private UserCreator creator;

    @MockBean
    private UserRespository repository;

    @Test
    public void createUserTest() throws UserExistException {
        Optional<User> optionalUser = Optional.empty();
        Mockito.when(repository.findById(Mockito.any())).thenReturn(optionalUser);
        User user = creator.create();
        Assertions.assertNotNull(user);
    }

    @Test
    public void createUserUserExistExceptionTest() throws UserExistException {
        Optional<User> optionalUser = Optional.of(new User());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(optionalUser);
        assertThrows(UserExistException.class, () -> creator.create());

    }

}