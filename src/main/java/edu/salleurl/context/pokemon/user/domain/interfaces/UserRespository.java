package edu.salleurl.context.pokemon.user.domain.interfaces;

import edu.salleurl.context.pokemon.user.domain.model.Id;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities.UserEntity;

import java.util.Optional;

public interface UserRespository {

    public Optional<User> findById(Id id);

    public void save(User user);
}
