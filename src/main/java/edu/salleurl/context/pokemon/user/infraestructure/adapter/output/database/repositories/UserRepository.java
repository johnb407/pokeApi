package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.repositories;

import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

public interface UserRepository extends CrudRepository<UserEntity,String> {

}
