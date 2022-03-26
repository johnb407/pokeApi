package edu.salleurl.context.pokemon.user.domain.service;

import edu.salleurl.context.pokemon.user.application.AddFavoriteUseCase;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
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

@SpringBootTest(classes = {UserFinder.class})
class UserFinderTest {

    @Autowired
    private UserFinder finder;

    @MockBean
    private UserRespository repository;

    @Test
    public void findUserTest() throws UserDontExistException {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(new User()));
        User uId = finder.find("uId");
        Assertions.assertNotNull(uId);
    }

    @Test
    public void findUserUserDontExistExceptionTest() throws UserDontExistException {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(UserDontExistException.class, () -> finder.find("uId"));
    }

}