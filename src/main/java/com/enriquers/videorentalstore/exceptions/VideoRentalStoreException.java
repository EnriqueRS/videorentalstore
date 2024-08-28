package com.enriquers.videorentalstore.exceptions;

public class VideoRentalStoreException extends RuntimeException {
  public static final String FILM_NOT_FOUND = "Film not found: %s";
  public static final String CUSTOMER_NOT_FOUND = "Customer not found";
  public static final String FILM_TYPE_UNKNOW = "Film type unknown: %s";

  public VideoRentalStoreException(String message) {
    super(message);
  }

  public VideoRentalStoreException(String message, String title) {
    super(String.format(message, title));
  }
}
