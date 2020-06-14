package testgroup.springdemo.controller;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import testgroup.springdemo.model.CustomerErrorResponse;
import testgroup.springdemo.model.exception.CustomerNotFoundException;

/**
 *
 * @author smallad
 */
@ControllerAdvice
public class CustomerRestExceptionHendler {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
        CustomerErrorResponse response = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                Instant.now().getEpochSecond());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
        CustomerErrorResponse response = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                Instant.now().getEpochSecond());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
