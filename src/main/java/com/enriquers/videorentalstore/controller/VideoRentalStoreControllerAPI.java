package com.enriquers.videorentalstore.controller;

import static com.enriquers.videorentalstore.controller.VideoRentalStoreControllerAPI.BASE_URL;

import com.enriquers.videorentalstore.model.dto.customer.CustomerDto;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnRequestDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(BASE_URL)
@Tag(name = "VideoRentalStoreController", description = "Controller for managing video rental store")
public interface VideoRentalStoreControllerAPI {
  public static final String BASE_URL = "/api/v1";
  public static final String FILMS_URL = "/films";
  public static final String RENTAL_URL = "/rental";
  public static final String RETURN_URL = "/return";
  public static final String CUSTOMERS_URL = "/customers";
  public static final String GET_CUSTOMER_URL = "/{customerId}";


  @GetMapping(FILMS_URL)
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully retrieved video rental store information",
  content = @Content(schema = @Schema(implementation = FilmDto.class)))
  List<FilmDto> getAllFilms();

  @PostMapping(RENTAL_URL)
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully rented films",
  content = @Content(schema = @Schema(implementation = RentalResponseDto.class)))
  RentalResponseDto rentFilms(@Valid @RequestBody RentalRequestDto rentalRequestDto);

  @PostMapping(RETURN_URL)
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully returned films",
  content = @Content(schema = @Schema(implementation = ReturnResponseDto.class)))
  ReturnResponseDto returnFilms(@Valid @RequestBody ReturnRequestDto returnRequestDto);

  @GetMapping(CUSTOMERS_URL)
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully retrieved customers information",
      content = @Content(schema = @Schema(implementation = CustomerDto.class)))
  List<CustomerDto> getAllCustomers();

  @GetMapping(CUSTOMERS_URL + GET_CUSTOMER_URL)
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully retrieved customers information",
      content = @Content(schema = @Schema(implementation = CustomerDto.class)))
  CustomerDto getCustomer(@NotNull  @PathVariable("customerId") long customerId);

}
