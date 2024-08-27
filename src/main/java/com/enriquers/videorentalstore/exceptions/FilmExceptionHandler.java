package com.enriquers.videorentalstore.exceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class FilmExceptionHandler {

  @ExceptionHandler(FilmException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public FilmError handlePriceException(FilmException e) {
    log.error("Price exception", e);
    return new FilmError(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public FilmError handleValidationException(MethodArgumentNotValidException e) {
    log.error("Validation exception", e);
    FilmError filmError = new FilmError("Validation error");

    List<String> errors = new ArrayList<>();
    e.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
    filmError.setErrors(errors);

    return filmError;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public FilmError handleException(Exception e) {
    log.error("Internal server error", e);
    return new FilmError(e.getMessage());
  }
}
