package edu.salleurl.context.pokemon.information.domain.exception;

public class RepositoryNotRespondingException extends Exception{
    public RepositoryNotRespondingException(String errorMessage) {
        super(errorMessage);
    }
}
