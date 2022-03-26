package edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest.dto;

import java.util.List;

public class GetTypesResponse {
    private List<String> types;

    public GetTypesResponse() {
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
