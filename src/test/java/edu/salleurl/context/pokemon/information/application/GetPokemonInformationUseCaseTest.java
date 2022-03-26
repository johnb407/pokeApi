package edu.salleurl.context.pokemon.information.application;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.service.PokemonFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {GetPokemonInformationUseCase.class})
class GetPokemonInformationUseCaseTest {

    @Autowired
    private GetPokemonInformationUseCase useCase;

    @MockBean
    private PokemonFinder finder;

    @Test
    public void getPokemonInformationUseCaseTest() throws NotFoundException, RepositoryNotRespondingException {


        Pokemon pokemonRequest = new Pokemon(1, "name", 1,1);
        Mockito.when(finder.findComplete(Mockito.any())).thenReturn(pokemonRequest);
        Pokemon pokemonResponse = useCase.execute("10");

        Assertions.assertEquals(1, pokemonResponse.getId().getValue());
        Assertions.assertEquals("name", pokemonResponse.getName().getValue() );
        Assertions.assertEquals(1, pokemonResponse.getWeight().getValue() );
        Assertions.assertEquals(1, pokemonResponse.getHeight().getValue() );
    }

    @Test
    public void getPokemonInformationUseCaseNotRespondingExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Mockito.when(finder.findComplete(Mockito.any())).thenThrow(new RepositoryNotRespondingException(""));
        assertThrows(RepositoryNotRespondingException.class, () ->  useCase.execute("10"));
    }

    @Test
    public void getPokemonInformationUseCaseNotFoundExceptionTest() throws NotFoundException, RepositoryNotRespondingException {
        Mockito.when(finder.findComplete(Mockito.any())).thenThrow(new NotFoundException(""));
        assertThrows(NotFoundException.class, () ->  useCase.execute("10"));
    }


}