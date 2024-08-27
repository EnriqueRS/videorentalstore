package com.enriquers.videorentalstore.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class FilmExceptionHandler {

  @ExceptionHandler(FilmNotFoundException.class)
  public ResponseEntity<FilmError> handlePriceException(FilmNotFoundException e) {
    log.error("Price exception", e);
    FilmError error = new FilmError(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<FilmError> handleException(Exception e) {
    log.error("Internal server error", e);
    FilmError error = new FilmError(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
