package edu.salleurl.context.pokemon.information.infraestructure.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.ports.PokemonRepository;
import edu.salleurl.context.pokemon.information.infraestructure.adapters.input.rest.dto.PokemonInformationResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetPokemonInformationControllerTest {

    @Autowired
    private GetPokemonInformationController controller;

    @Autowired
    private ErrorInfoController errorHandlerController;

    @MockBean
    private PokemonRepository repository;

    @Test
    public void getPokemonInformationTest() throws Exception {

        Pokemon pokemon = new Pokemon(1, "name", 1,1);

        Mockito.when(repository.getPokemonInformation(any())).thenReturn(Optional.of(pokemon));
        String uri = "/api/pokemon/information/10";
        EntityExchangeResult<byte[]> resultRest =WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk().expectBody().returnResult();

        ObjectMapper objectMapper = new ObjectMapper();
        PokemonInformationResponse response = objectMapper.readValue(new String(resultRest.getResponseBody()), PokemonInformationResponse.class);
        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals("name", response.getName());
        Assertions.assertEquals(1, response.getWeight());
        Assertions.assertEquals(1, response.getHeight() );
    }

    @Test
    public void getTypesPokemonNotFoundTest() throws Exception {

        Mockito.when(repository.getPokemonInformation(any())).thenReturn(Optional.empty());
        String uri = "/api/pokemon/information/10";
        WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .get()
                .uri(uri)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void getTypesPokemonTimeoutTest() throws Exception {


       Mockito.when(repository.getPokemonInformation(any())).thenThrow( new RepositoryNotRespondingException("Not exists"));
        String uri = "/api/pokemon/information/10";
        EntityExchangeResult<byte[]> entityExchangeResult = WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .get()
                .uri(uri)
                .exchange()
                .expectBody().returnResult();

        assertEquals(500,entityExchangeResult.getStatus().value());
    }

}