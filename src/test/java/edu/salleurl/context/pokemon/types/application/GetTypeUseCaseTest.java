package edu.salleurl.context.pokemon.types.application;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Type;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.domain.model.service.TypesFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {GetTypeUseCase.class})
class GetTypeUseCaseTest {

    @Autowired
    private GetTypeUseCase useCase;

    @MockBean
    private TypesFinder finder;

    @Test
    public void getTypeUseCaseTest() throws NotFoundException, RepositoryNotRespondingException {

        Types types = new Types();
        Type type = new Type("Electric");
        types.add(type);
        Mockito.when(finder.find(Mockito.any())).thenReturn(types);
        Types typesResult = useCase.execute("pokemonName");

        Assertions.assertEquals(typesResult.getTypes().size(), 1);
        Assertions.assertEquals(typesResult.getTypes().get(0).getType(), "Electric");
    }

    @Test
    public void getTypeUseCaseRepositoryNotRespondingExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Mockito.when(finder.find(Mockito.any())).thenThrow(new RepositoryNotRespondingException(""));
        assertThrows(RepositoryNotRespondingException.class, () ->  useCase.execute("pokemonName"));
    }

    @Test
    public void getTypeUseCaseNotFoundExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Mockito.when(finder.find(Mockito.any())).thenThrow(new NotFoundException(""));
        assertThrows(NotFoundException.class, () ->  useCase.execute("pokemonName"));
    }


}