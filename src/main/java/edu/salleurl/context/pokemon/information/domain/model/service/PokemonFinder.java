package edu.salleurl.context.pokemon.information.domain.model.service;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Id;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.ports.FavoritePokemonRepository;
import edu.salleurl.context.pokemon.information.domain.model.ports.PokemonRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PokemonFinder {

    private final PokemonRepository repository;

    private final FavoritePokemonRepository favoritePokemonRepository;

    public PokemonFinder(PokemonRepository repository, FavoritePokemonRepository favoritePokemonRepository) {
        this.repository = repository;
        this.favoritePokemonRepository = favoritePokemonRepository;
    }

    public Pokemon findComplete(String id) throws RepositoryNotRespondingException, NotFoundException {

        Optional<Pokemon> pokemonInformation = repository.getPokemonInformation(new Id(Integer.valueOf(id)));

        if(pokemonInformation.isPresent()){
            Pokemon pokemon = pokemonInformation.get();
            pokemon.setFavoriteCount(favoritePokemonRepository.getPokemonFavoriteCount(pokemon.getId()));
            return pokemon;
        }else{
            throw new NotFoundException("Id not found");
        }
    }

    public Pokemon findOnlyFavorite(String id) throws RepositoryNotRespondingException, NotFoundException {
            Pokemon pokemon = new Pokemon(new Id(Integer.valueOf(id)));
            pokemon.setFavoriteCount(favoritePokemonRepository.getPokemonFavoriteCount(pokemon.getId()));
            return pokemon;
    }
}
