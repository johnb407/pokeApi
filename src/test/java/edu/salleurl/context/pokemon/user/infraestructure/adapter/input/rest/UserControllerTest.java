package edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.CreateUserResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTest {

    @Autowired
    private UserController userController;


    @Test
    public void createUserTest() throws Exception {
        String uri = "/api/pokemon/user/create-user";
        EntityExchangeResult<byte[]> result =WebTestClient.bindToController(userController).build()
                .post()
                .uri(uri)
                .exchange()
                .expectStatus().isOk().expectBody().returnResult();

        ObjectMapper objectMapper = new ObjectMapper();
        CreateUserResponse createUserResponse = objectMapper.readValue(new String(result.getResponseBody()), CreateUserResponse.class);
        assertFalse(createUserResponse.getId().isEmpty());
        //objectMapper.writeValueAsString(obj);

    }

}