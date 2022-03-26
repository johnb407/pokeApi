package edu.salleurl.context.pokemon.types.domain.model.service;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.domain.model.Name;
import edu.salleurl.context.pokemon.types.domain.model.Types;
import edu.salleurl.context.pokemon.types.domain.model.interfaces.ITypesRepository;
import org.springframework.stereotype.Component;

@Component
public class TypesFinder {

    private final ITypesRepository repository;

    public TypesFinder(ITypesRepository repository) {
        this.repository = repository;
    }

    public Types find(Name name) throws RepositoryNotRespondingException, NotFoundException {

        return repository.getPokemonTypes(name);
    }
}
