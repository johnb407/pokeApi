package edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest;

import edu.salleurl.context.pokemon.types.domain.exception.NotFoundException;
import edu.salleurl.context.pokemon.types.domain.exception.RepositoryNotRespondingException;
import edu.salleurl.context.pokemon.types.infraestructure.adapters.input.rest.dto.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorController {

    /*@ExceptionHandler({Exception.class})  //Generic error
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {

        return new ResponseEntity(new ErrorResponse("Something was wrong, please call the administrator", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleExceptionNotFound(NotFoundException exception) {

        return new ResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RepositoryNotRespondingException.class})
    public ResponseEntity<ErrorResponse> handleExceptionRepository(RepositoryNotRespondingException exception) {

        return new ResponseEntity(new ErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
