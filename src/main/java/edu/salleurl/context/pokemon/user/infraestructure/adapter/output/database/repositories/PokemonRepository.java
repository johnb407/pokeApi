package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.repositories;

import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities.FavoritePokemonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<FavoritePokemonEntity,String> {

}
