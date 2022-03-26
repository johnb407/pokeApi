package edu.salleurl.context.pokemon.user.domain.exceptions;

public class PokemonFavoriteExistsException extends Exception{
    public PokemonFavoriteExistsException(String message){
        super(message);
    }
}
