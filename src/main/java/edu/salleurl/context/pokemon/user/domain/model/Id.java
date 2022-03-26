package edu.salleurl.context.pokemon.user.domain.model;

import java.util.UUID;

public class Id {

    private String id;

    public Id(){
        id = UUID.randomUUID().toString();
    }

    public Id(String id){
        this.id= id;

    }

    public String getId(){
        return id;
    }
}
