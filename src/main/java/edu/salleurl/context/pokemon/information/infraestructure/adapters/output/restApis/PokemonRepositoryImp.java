package edu.salleurl.context.pokemon.information.infraestructure.adapters.output.restApis;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Id;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.ports.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Repository
public class PokemonRepositoryImp implements PokemonRepository {

    private final static String URL = "https://pokeapi.co/api/v2/pokemon/";

    public Optional<Pokemon> getPokemonInformation(Id pokemonId) throws RepositoryNotRespondingException, NotFoundException {

        HttpResponse<String> response = null;
        try {

            HttpClient httpClient = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL + pokemonId.getValue()))
                    .GET()
                    .build();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.NOT_FOUND.value()) {
                return Optional.empty();
            }

            edu.salleurl.context.pokemon.information.infraestructure.adapters.output.restApis.dto.Pokemon pokemon = mapper.readValue(response.body(), edu.salleurl.context.pokemon.information.infraestructure.adapters.output.restApis.dto.Pokemon.class);

            Pokemon pokemonResponse = new Pokemon(pokemon.getId(), pokemon.getName(), pokemon.getWeight(), pokemon.getHeight());
            return Optional.of(pokemonResponse);

        } catch (IOException e) {
            throw new RepositoryNotRespondingException("Error with Api Communication");
        } catch (InterruptedException e) {
            throw new RepositoryNotRespondingException("Error with Api Communication");
        } catch (URISyntaxException e) {
            throw new RepositoryNotRespondingException("Error creating url");
        }

    }
}


