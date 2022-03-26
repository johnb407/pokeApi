package edu.salleurl.context.pokemon.information.infraestructure.adapters.input.rest;

import edu.salleurl.context.pokemon.information.application.GetPokemonInformationUseCase;
import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.infraestructure.adapters.input.rest.dto.PokemonInformationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/pokemon/information")
public class GetPokemonInformationController {

    final private GetPokemonInformationUseCase useCase;


    public GetPokemonInformationController(GetPokemonInformationUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping( path = "/{pokemonId}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<PokemonInformationResponse> GetPokemonInformation(@PathVariable String pokemonId) throws RepositoryNotRespondingException, NotFoundException {

        Pokemon pokemon = useCase.execute(pokemonId);

        return ResponseEntity.ok(new PokemonInformationResponse(
                pokemon.getId().getValue(),
                pokemon.getName().getValue(),
                pokemon.getWeight().getValue(),
                pokemon.getHeight().getValue(),
                pokemon.getFavoriteCount().getCount()));


    }
}
