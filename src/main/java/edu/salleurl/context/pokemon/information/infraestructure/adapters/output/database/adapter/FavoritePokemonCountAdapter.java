package edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.adapter;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.FavoriteCount;
import edu.salleurl.context.pokemon.information.domain.model.ports.FavoritePokemonRepository;
import edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.entities.FavoritePokemonCountEntity;
import edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.repositories.FavoritePokemonCountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FavoritePokemonCountAdapter implements FavoritePokemonRepository {

    private final FavoritePokemonCountRepository repository;

    public FavoritePokemonCountAdapter(FavoritePokemonCountRepository repository) {
        this.repository = repository;
    }


    @Override
    public FavoriteCount getPokemonFavoriteCount(edu.salleurl.context.pokemon.information.domain.model.Id id) throws RepositoryNotRespondingException, NotFoundException {
        Optional<FavoritePokemonCountEntity> favoritePokemonEntity = repository.findById(String.valueOf(id.getValue()));
        if (favoritePokemonEntity.isPresent()) {
            return new FavoriteCount(favoritePokemonEntity.get().getCount());
        } else {
            return new FavoriteCount();
        }
    }

    @Override
    public void save(edu.salleurl.context.pokemon.information.domain.model.Pokemon pokemon) throws RepositoryNotRespondingException, NotFoundException {
      repository.save(new FavoritePokemonCountEntity(String.valueOf(pokemon.getId().getValue()), pokemon.getFavoriteCount().getCount()));

    }
}
