package edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest;

import edu.salleurl.context.pokemon.types.application.GetTypeUseCase;
import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest.dto.GetTypesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/pokemon/types")
public class GetTypesRestController {

    final private GetTypeUseCase useCase;


    public GetTypesRestController(GetTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping( path = "/{pokemonName}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<GetTypesResponse> GetTypes(@PathVariable String pokemonName) throws RepositoryNotRespondingException, NotFoundException {

        Types types = useCase.execute(pokemonName);
        List<String> typesList = types.getTypes().stream().map(x -> x.getType()).collect((Collectors.toList()));
        GetTypesResponse response = new GetTypesResponse();
        response.setTypes(typesList);
        return ResponseEntity.ok(response);

    }
}
