package edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.salleurl.context.pokemon.user.application.AddFavoriteUseCase;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.AddFavoritePokemonRequest;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.CreateUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AddFavoritePokemonControllerTest {

    @Autowired
    private AddFavoritePokemonController controller;

    @Autowired
    private ErrorUserController errorHandlerController;

    @Autowired
    private UserController userController;

    @Test
    @Transactional
    public void AddFavoritePokemonTest() throws Exception {

        AddFavoritePokemonRequest addFavoritePokemonRequest = new AddFavoritePokemonRequest();
        addFavoritePokemonRequest.setPokemonId("123");

        String uri = "/api/pokemon/user/add-favorite-pokemon";
        WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .post()
                .uri(uri)
                .header("user", createUser())
                .bodyValue(addFavoritePokemonRequest)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void AddFavoritePokemonUserNotFoundTest() throws Exception {

        AddFavoritePokemonRequest addFavoritePokemonRequest = new AddFavoritePokemonRequest();
        addFavoritePokemonRequest.setPokemonId("123");

        String uri = "/api/pokemon/user/add-favorite-pokemon";
        WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .post()
                .uri(uri)
                .header("user", "1234")
                .bodyValue(addFavoritePokemonRequest)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Transactional
    public void AddFavoritePokemonDuplicateTest() throws Exception {

        AddFavoritePokemonRequest addFavoritePokemonRequest = new AddFavoritePokemonRequest();
        addFavoritePokemonRequest.setPokemonId("123");
        String user = createUser();
        String uri = "/api/pokemon/user/add-favorite-pokemon";
        WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .post()
                .uri(uri)
                .header("user", user )
                .bodyValue(addFavoritePokemonRequest)
                .exchange()
                .expectStatus().isOk();

        WebTestClient.bindToController(controller).controllerAdvice(errorHandlerController).build()
                .post()
                .uri(uri)
                .header("user", user )
                .bodyValue(addFavoritePokemonRequest)
                .exchange()
                .expectStatus().isBadRequest();
    }



    public String createUser() throws Exception {
        String uri = "/api/pokemon/user/create-user";
        EntityExchangeResult<byte[]> result = WebTestClient.bindToController(userController).build()
                .post()
                .uri(uri)
                .exchange()
                .expectStatus().isOk().expectBody().returnResult();

        ObjectMapper objectMapper = new ObjectMapper();
        CreateUserResponse createUserResponse = objectMapper.readValue(new String(result.getResponseBody()), CreateUserResponse.class);
        return createUserResponse.getId();
    }
}