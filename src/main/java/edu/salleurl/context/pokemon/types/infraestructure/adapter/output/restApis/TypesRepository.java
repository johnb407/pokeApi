package edu.salleurl.context.pokemon.types.infraestructure.adapter.output.restApis;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Name;
import edu.salleurl.context.pokemon.types.domain.model.Type;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.domain.model.interfaces.ITypesRepository;
import edu.salleurl.context.pokemon.types.infraestructure.adapter.output.restApis.dto.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Repository
public class TypesRepository implements ITypesRepository {

    private final static String URL = "https://pokeapi.co/api/v2/pokemon/";

    public Types getPokemonTypes(Name pokemonName) throws RepositoryNotRespondingException, NotFoundException {

        HttpResponse<String> response = null;
        try {

            HttpClient httpClient = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL + pokemonName.getName()))
                    .GET()
                    .build();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.NOT_FOUND.value()){
                throw new NotFoundException("Pokemon doesn't Exists");
            }

            Pokemon pokemon = mapper.readValue(response.body(), Pokemon.class);

            Types types = new Types();
            pokemon.getTypes().stream().forEach(x -> {
                Type type = new Type(x.getType().getName());
                types.add(type);
            });
            return types;

        } catch (IOException e) {
            throw new RepositoryNotRespondingException("Error with Api Communication");
        } catch (InterruptedException e) {
            throw new RepositoryNotRespondingException("Error with Api Communication");
        } catch (URISyntaxException e) {
            throw new RepositoryNotRespondingException("Error creating url");
        }


    }

}
