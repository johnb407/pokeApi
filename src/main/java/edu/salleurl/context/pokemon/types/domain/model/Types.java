package edu.salleurl.context.pokemon.types.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Types {

    private List<Type> types;

    public Types(){
        types = new ArrayList<>();
    }

    public void add(Type type){
        types.add(type);
    }

    public List<Type> getTypes() {
        return types;
    }
}
