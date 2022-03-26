package edu.salleurl.context.pokemon.information.application;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.service.FavoritePokemonSaver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {IncreaseFavoritePokemonUseCase.class})
class IncreaseFavoritePokemonUseCaseTest {

    @Autowired
    private IncreaseFavoritePokemonUseCase useCase;

    @MockBean
    private FavoritePokemonSaver saver;

    @Test
    public void increase() throws NotFoundException, RepositoryNotRespondingException {

        useCase.execute("1");

    }

}