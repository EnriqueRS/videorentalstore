package com.enriquers.videorentalstore.exceptions;

public class FilmNotFoundException extends RuntimeException {
  public static final String FILM_NOT_FOUND = "Film not found: %s";
  public FilmNotFoundException(String title) {
    super(String.format(FILM_NOT_FOUND, title));
  }
}
