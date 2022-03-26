package edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest;

import edu.salleurl.context.pokemon.user.application.AddFavoriteUseCase;
import edu.salleurl.context.pokemon.user.application.CreateUserUseCase;
import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.AddFavoritePokemonRequest;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.CreateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/pokemon/user")
public class AddFavoritePokemonController {

    private final AddFavoriteUseCase useCase;

    public AddFavoritePokemonController(AddFavoriteUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(path = "/add-favorite-pokemon")
    public ResponseEntity<CreateUserResponse> addFavoritePokemon(@RequestBody AddFavoritePokemonRequest request, @RequestHeader("user") String user) throws UserDontExistException, PokemonFavoriteExistsException {
        useCase.execute(user, request.getPokemonId());
        return ResponseEntity.ok().build();
    }

}
