package edu.salleurl.context.pokemon.user.domain.service;

import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import edu.salleurl.context.pokemon.user.domain.interfaces.UserRespository;
import edu.salleurl.context.pokemon.user.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCreator {

    private final UserRespository repository;

    public UserCreator(UserRespository repository) {
        this.repository = repository;
    }

    public User create() throws UserExistException {
        User user  = new User();
        validateExists(user);
        repository.save(user);
        return user;
    }

    private void validateExists(User user) throws UserExistException {
        Optional<User> byId = repository.findById(user.getId());
        if(byId.isPresent()){
            throw new UserExistException();
        }

    }
}
