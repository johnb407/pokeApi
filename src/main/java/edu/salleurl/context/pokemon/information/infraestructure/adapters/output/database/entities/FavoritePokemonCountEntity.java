package edu.salleurl.context.pokemon.information.infraestructure.adapters.output.database.entities;

import javax.persistence.*;

@Entity
@Table(name = "favorite_pokemon_count")
public class FavoritePokemonCountEntity {
    public FavoritePokemonCountEntity() {
    }

    public FavoritePokemonCountEntity(String id, Integer count) {
        this.id = id;
        this.count = count;
    }

    @Id
    private String id;

    @Column(name = "count", nullable = false)
    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
