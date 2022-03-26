package edu.salleurl.context.pokemon.user.infraestructure.adapter.output.database.entities;

import javax.persistence.*;

@Entity
@Table(name = "favorite_pokemon")
public class FavoritePokemonEntity {
    public FavoritePokemonEntity() {
    }

    public FavoritePokemonEntity(String id, String userId) {
        this.id=id;
        this.user = new UserEntity(userId);
    }
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
