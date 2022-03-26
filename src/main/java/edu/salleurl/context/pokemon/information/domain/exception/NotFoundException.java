package edu.salleurl.context.pokemon.information.domain.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
