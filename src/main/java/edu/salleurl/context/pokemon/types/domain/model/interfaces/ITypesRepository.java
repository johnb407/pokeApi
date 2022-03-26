package edu.salleurl.context.pokemon.types.domain.model.interfaces;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Name;
import edu.salleurl.context.pokemon.types.domain.model.Types;

public interface ITypesRepository {

    public Types getPokemonTypes(Name pokemonName) throws RepositoryNotRespondingException, NotFoundException;
}
