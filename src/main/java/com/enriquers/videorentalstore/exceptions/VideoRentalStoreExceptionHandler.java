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
public class VideoRentalStoreExceptionHandler {

  @ExceptionHandler(VideoRentalStoreException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public VideoRentalStoreError handlePriceException(VideoRentalStoreException e) {
    log.error("Price exception", e);
    return new VideoRentalStoreError(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public VideoRentalStoreError handleValidationException(MethodArgumentNotValidException e) {
    log.error("Validation exception", e);
    VideoRentalStoreError videoRentalStoreError = new VideoRentalStoreError("Validation error");

    List<String> errors = new ArrayList<>();
    e.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
    videoRentalStoreError.setErrors(errors);

    return videoRentalStoreError;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public VideoRentalStoreError handleException(Exception e) {
    log.error("Internal server error", e);
    return new VideoRentalStoreError(e.getMessage());
  }
}
