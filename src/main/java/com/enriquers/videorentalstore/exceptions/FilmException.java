package com.enriquers.videorentalstore.exceptions;

public class FilmException extends RuntimeException {
  public static final String FILM_NOT_FOUND = "Film not found: %s";
  public static final String FILMS_LIST_EMPTY = "The list of films is empty, at least one film is required";

  public FilmException(String message) {
    super(message);
  }

  public FilmException(String message, String title) {
    super(String.format(message, title));
  }
}
