package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.adapter;

import edu.salleurl.context.pokemon.user.domain.interfaces.UserRespository;
import edu.salleurl.context.pokemon.user.domain.model.Id;
import edu.salleurl.context.pokemon.user.domain.model.User;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities.FavoritePokemonEntity;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities.UserEntity;
import edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryAdapter implements UserRespository {

    private final UserRepository repository;

    public UserRepositoryAdapter(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findById(Id id) {
        Optional<UserEntity> userEntity = repository.findById(id.getId());
        if (userEntity.isPresent()) {
            return Optional.of(new User(userEntity.get().getId(), userEntity.get().getFavorites().stream().map(x -> x.getId()).collect(Collectors.toSet())));
        } else {
            return Optional.empty();
        }
    }

    public void save(User user) {
        UserEntity userEntity = new UserEntity(user.getId().getId(), user.getIdFavorites().stream().map(x -> new FavoritePokemonEntity( x, user.getId().getId())).collect(Collectors.toSet())); //Todo send favorites
        repository.save(userEntity);
    }
}
