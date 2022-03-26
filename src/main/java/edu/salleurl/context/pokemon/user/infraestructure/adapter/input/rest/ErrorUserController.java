package edu.salleurl.context.pokemon.user.infraestructure.adapter.input.rest;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest.dto.ErrorResponse;
import edu.salleurl.context.pokemon.user.domain.exceptions.PokemonFavoriteExistsException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserDontExistException;
import edu.salleurl.context.pokemon.user.domain.exceptions.UserExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorUserController {

    @ExceptionHandler({UserDontExistException.class})
    public ResponseEntity<ErrorResponse> handleUserDontExistException(UserDontExistException exception) {

        return new ResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PokemonFavoriteExistsException.class})
    public ResponseEntity<ErrorResponse> handlePokemonFavoriteExistsException(PokemonFavoriteExistsException exception) {

        return new ResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserExistException.class})
    public ResponseEntity<ErrorResponse> handleUserExistException(UserExistException exception) {

        return new ResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                HttpStatus.BAD_REQUEST);
    }
}
