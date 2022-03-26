package edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Type;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.domain.model.interfaces.ITypesRepository;
import edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest.dto.GetTypesResponse;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.ErrorUserController;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.AddFavoritePokemonRequest;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.CreateUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetTypesRestControllerTest {

    @Autowired
    private GetTypesRestController controller;

    @Autowired
    private ErrorController errorHandlerController;

    @MockBean
    private ITypesRepository repository;

    @Test
    public void getTypesTest() throws Exception {

        AddFavoritePokemonRequest addFavoritePokemonRequest = new AddFavoritePokemonRequest();
        addFavoritePokemonRequest.setPokemonId("123");

        Types result = new Types();
        result.add(new Type("electric"));
        Mockito.when(repository.getPokemonTypes(any())).thenReturn(result);
        String uri = "/api/pokemon/types/pokemon123";
        EntityExchangeResult<byte[]> resultRest =WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk().expectBody().returnResult();

        ObjectMapper objectMapper = new ObjectMapper();
        GetTypesResponse response = objectMapper.readValue(new String(resultRest.getResponseBody()), GetTypesResponse.class);
        assertEquals(response.getTypes().size(),1);
    }

    @Test
    public void getTypesPokemonNotFoundTest() throws Exception {

        AddFavoritePokemonRequest addFavoritePokemonRequest = new AddFavoritePokemonRequest();
        addFavoritePokemonRequest.setPokemonId("123");

        Types result = new Types();
        result.add(new Type("electric"));
        Mockito.when(repository.getPokemonTypes(any())).thenThrow( new NotFoundException("Not exists"));
        String uri = "/api/pokemon/types/pokemon123";
        WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .get()
                .uri(uri)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void getTypesPokemonTimeoutTest() throws Exception {

        AddFavoritePokemonRequest addFavoritePokemonRequest = new AddFavoritePokemonRequest();
        addFavoritePokemonRequest.setPokemonId("123");

        Mockito.when(repository.getPokemonTypes(any())).thenThrow( new RepositoryNotRespondingException("Not exists"));
        String uri = "/api/pokemon/types/pokemon123";
        EntityExchangeResult<byte[]> entityExchangeResult = WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .get()
                .uri(uri)
                .exchange()
                .expectBody().returnResult();

        assertEquals(500,entityExchangeResult.getStatus().value());
    }

}