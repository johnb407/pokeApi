package edu.salleurl.context.pokemon.types.domain.model.service;

import edu.salleurl.context.pokemon.types.application.GetTypeUseCase;
import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Name;
import edu.salleurl.context.pokemon.types.domain.model.Type;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.domain.model.interfaces.ITypesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TypesFinder.class})
class TypesFinderTest {

    @Autowired
    private TypesFinder finder;

    @MockBean
    private ITypesRepository repository;

    @Test
    public void findTest() throws NotFoundException, RepositoryNotRespondingException {
        Types types = new Types();
        Type type = new Type("Electric");
        types.add(type);
        Name name = new Name("PokemonName");

        Mockito.when(repository.getPokemonTypes(name)).thenReturn(types);
        Types typesResult = finder.find(name);

        Assertions.assertEquals(typesResult.getTypes().size(), 1);
        Assertions.assertEquals(typesResult.getTypes().get(0).getType(), "Electric");
    }

    @Test
    public void findTestRepositoryNotRespondingExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Name name = new Name("PokemonName");
        Mockito.when(repository.getPokemonTypes(name)).thenThrow(new RepositoryNotRespondingException(""));
        assertThrows(RepositoryNotRespondingException.class, () -> finder.find(name));
    }

    @Test
    public void findTestNotFoundExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Name name = new Name("PokemonName");
        Mockito.when(repository.getPokemonTypes(name)).thenThrow(new NotFoundException(""));
        assertThrows(NotFoundException.class, () ->  finder.find(name));
    }

}