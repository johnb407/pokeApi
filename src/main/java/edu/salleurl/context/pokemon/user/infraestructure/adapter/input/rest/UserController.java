package edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest.dto.GetTypesResponse;
import edu.salleurl.context.pokemon.user.application.CreateUserUseCase;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto.CreateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/pokemon/user")
public class UserController {

    private final CreateUserUseCase useCase;

    public UserController(CreateUserUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(path = "/create-user")
    public ResponseEntity<CreateUserResponse> createCart() throws UserExistException {
        User user = useCase.excecute();
        return ResponseEntity.ok(new CreateUserResponse(user.getId().getId()));
    }

}
