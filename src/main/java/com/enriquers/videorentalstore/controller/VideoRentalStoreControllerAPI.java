package com.enriquers.videorentalstore.controller;

import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1")
@Tag(name = "VideoRentalStoreController", description = "Controller for managing video rental store")
public interface VideoRentalStoreControllerAPI {

  @GetMapping("/films")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully retrieved video rental store information",
  content = @Content(schema = @Schema(implementation = FilmDto.class)))
  List<FilmDto> getAllFilms();

  @PostMapping("/rental")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Successfully rented films",
  content = @Content(schema = @Schema(implementation = RentalResponseDto.class)))
  RentalResponseDto rentFilms(@Valid @RequestBody RentalRequestDto rentalRequestDto);

}
