package com.enriquers.videorentalstore;

import com.enriquers.videorentalstore.model.dto.customer.CustomerDto;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnRequestDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperTest {

  private final String PATH = "./src/test/java/com/enriquers/videorentalstore/resources/json/";
  private final String GET_FILMS_EXPECTED_JSON_NAME = "film/getFilmsExpected.json";
  private final String REQUEST_BODY_RENTAL_JSON_NAME = "rental/requestBodyRental.json";
  private final String RESPONSE_BODY_RENTAL_JSON_NAME = "rental/responseBodyRental.json";
  private final String REQUEST_BODY_RETURN_JSON_NAME = "returnfilms/requestBodyReturn.json";
  private final String RESPONSE_BODY_RETURN_JSON_NAME = "returnfilms/responseBodyReturn.json";
  private final String GET_CUSTOMERS_EXPECTED_JSON_NAME = "customer/getCustomersExpected.json";

  public List<FilmDto> getFilmsExpected() {
    File file = new File(PATH, GET_FILMS_EXPECTED_JSON_NAME);

    ObjectMapper objectMapper = new ObjectMapper();
    List<FilmDto> films = null;
    try  {
      films = objectMapper.readValue(file, new TypeReference<List<FilmDto>>() {});
    } catch (IOException e) {
      e.printStackTrace();
    }
    return films;
  }

  public RentalRequestDto getRentRequest() {
    File file = new File(PATH, REQUEST_BODY_RENTAL_JSON_NAME);

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(file, RentalRequestDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public RentalResponseDto getRentalResponse() {
    File file = new File(PATH, RESPONSE_BODY_RENTAL_JSON_NAME);

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(file, RentalResponseDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public ReturnRequestDto getReturnRequest() {
    File file = new File(PATH, REQUEST_BODY_RETURN_JSON_NAME);

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(file, ReturnRequestDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public ReturnResponseDto getReturnResponse() {
    File file = new File(PATH, RESPONSE_BODY_RETURN_JSON_NAME);

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(file, ReturnResponseDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<CustomerDto> getCustomersExpected() {
    File file = new File(PATH, GET_CUSTOMERS_EXPECTED_JSON_NAME);

    ObjectMapper objectMapper = new ObjectMapper();
    List<CustomerDto> customers = null;
    try  {
      customers = objectMapper.readValue(file, new TypeReference<List<CustomerDto>>() {});
    } catch (IOException e) {
      e.printStackTrace();
    }
    return customers;
  }

  public CustomerDto getCustomerIdExpected() {
    List<CustomerDto> customers = getCustomersExpected();
    return customers.stream().filter(customer -> customer.getId() == 1L).findFirst().orElse(null);
  }
}
