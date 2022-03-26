package edu.salleurl.context.pokemon.information.domain.model.ports;

import edu.salleurl.context.pokemon.information.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.information.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.information.domain.model.Name;
import edu.salleurl.context.pokemon.information.domain.model.Pokemon;
import edu.salleurl.context.pokemon.information.domain.model.Id;

import java.util.Optional;

public interface PokemonRepository {

    public Optional<Pokemon> getPokemonInformation(Id pokemonId) throws RepositoryNotRespondingException, NotFoundException;
}
