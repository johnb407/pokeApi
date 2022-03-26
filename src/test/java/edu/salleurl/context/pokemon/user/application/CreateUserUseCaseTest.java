package edu.salleurl.context.pokemon.user.application;

import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.domain.service.UserCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CreateUserUseCase.class})
class CreateUserUseCaseTest {

    @Autowired
    private CreateUserUseCase useCase;

    @MockBean
    private UserCreator creator;

    @Test
    public void CreateUserTest() throws UserExistException {

        User user = new User();
        Mockito.when(creator.create()).thenReturn(user);
        User userResponse = useCase.excecute();

        Assertions.assertEquals(userResponse.getId().getId(), user.getId().getId());

    }

    @Test
    public void CreateUserUserExistExceptionTest() throws UserExistException {

        Mockito.when(creator.create()).thenThrow(new UserExistException());
        assertThrows(UserExistException.class, () -> useCase.excecute());

    }

}