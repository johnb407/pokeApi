package edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.repositories;

import edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.entities.FavoritePokemonCountEntity;
import org.springframework.data.repository.CrudRepository;

public interface FavoritePokemonCountRepository extends CrudRepository<FavoritePokemonCountEntity,String> {

}
