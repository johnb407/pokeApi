package edu.salleurl.context.pokemon.types.domain.exception;

public class RepositoryNotRespondingException extends Exception{
    public RepositoryNotRespondingException(String errorMessage) {
        super(errorMessage);
    }
}
