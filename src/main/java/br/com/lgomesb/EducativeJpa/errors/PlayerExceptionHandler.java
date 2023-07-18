package br.com.lgomesb.EducativeJpa.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class PlayerExceptionHandler {

    @ExceptionHandler
    ResponseEntity<PlayerErrorResponse> playerNotFound(PlayerNotFoundException ex, HttpServletRequest req){

        PlayerErrorResponse response = new PlayerErrorResponse();
        response.setTimestamp(ZonedDateTime.now());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setPath(req.getRequestURI());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> genericHandler (Exception ex, HttpServletRequest req) {
        PlayerErrorResponse response = new PlayerErrorResponse();
        response.setTimestamp(ZonedDateTime.now());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setPath(req.getRequestURI());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
}
