package edu.salleurl.context.pokemon.user.application;

import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.domain.service.UserCreator;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserCreator creator;

    public CreateUserUseCase(UserCreator creator) {
        this.creator = creator;
    }

    public User excecute() throws UserExistException {
        return creator.create();
    }
}
