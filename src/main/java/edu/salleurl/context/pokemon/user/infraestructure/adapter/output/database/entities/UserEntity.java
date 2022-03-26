package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    private String id;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<FavoritePokemonEntity> favorites = new HashSet<>();

    public UserEntity() {

    }

    public UserEntity(String id) {
        this.id = id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public UserEntity(String id, Set<FavoritePokemonEntity> favorites) {
        this.id = id;
        this.favorites = favorites;
    }

    public Set<FavoritePokemonEntity> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<FavoritePokemonEntity> favorites) {
        this.favorites = favorites;
    }
}
