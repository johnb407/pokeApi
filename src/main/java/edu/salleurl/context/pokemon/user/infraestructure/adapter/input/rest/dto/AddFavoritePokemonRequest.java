package edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest.dto;

public class AddFavoritePokemonRequest {

    private String pokemonId;

    public String getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(String pokemonId) {
        this.pokemonId = pokemonId;
    }
}
