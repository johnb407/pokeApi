package edu.salleurl.context.pokemon.user.domain.service;

import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
import edu.salleurl.context.pokemon.user.domain.interfaces.UserRespository;
import edu.salleurl.context.pokemon.user.domain.model.Id;
import edu.salleurl.context.pokemon.user.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFinder {

    private final UserRespository repository;

    public UserFinder(UserRespository repository) {
        this.repository = repository;
    }

    public User find(String id) throws UserDontExistException {
        Optional<User> byId = repository.findById(new Id(id));
        if(byId.isPresent()){
            return byId.get();
        }else{
            throw new UserDontExistException();
        }

    }
}
