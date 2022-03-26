package edu.salleurl.context.pokemon.user.domain.model;

import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Id id;

    private final Favorites favorites;

    public User(){
        this.id = new Id();
        this.favorites = new Favorites();
    }

    public User(String id, Set<String> pokemonIds){
        this.id = new Id(id);
        if(pokemonIds == null || pokemonIds.isEmpty() ){
            this.favorites = new Favorites();
        }else {
            this.favorites = new Favorites(pokemonIds);
        }
    }

    public void addFavorite(String pokemonId) throws PokemonFavoriteExistsException {
        this.favorites.add(pokemonId);
    }

    public Set<String> getIdFavorites(){
        return favorites.getIdFavorites();
    }

    public Id getId(){
        return this.id;
    }
}
