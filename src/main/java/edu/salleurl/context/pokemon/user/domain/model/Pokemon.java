package edu.salleurl.context.pokemon.user.domain.model;

import java.util.Objects;

public class Pokemon {

    private final Id id;

    public Pokemon(Id id) {
        this.id = id;
    }

    public Pokemon(String id) {
        this.id = new Id(id);
    }

    public Id getId() {
        return id;
    }

}
