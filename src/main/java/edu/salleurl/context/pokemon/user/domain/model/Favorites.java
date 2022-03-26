package edu.salleurl.context.pokemon.user.domain.model;

import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Favorites {

    private final Set<Pokemon> pokemons;

    public Favorites(Set<String> pokemons) {
        this.pokemons = pokemons.stream().map(x -> new Pokemon(x)).collect(Collectors.toSet());
    }

    public Favorites(){
        this.pokemons = new HashSet<>();
    }

    public void add(String id) throws PokemonFavoriteExistsException {
        if(pokemons.stream().anyMatch(x -> x.getId().getId().equals(id))){
            throw new PokemonFavoriteExistsException("Pokemon Already Exists");
        }
        pokemons.add(new Pokemon(id));
    }

    public Set<String> getIdFavorites(){
        return pokemons.stream().map(x -> x.getId().getId()).collect(Collectors.toSet());
    }
}
