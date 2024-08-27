package com.enriquers.videorentalstore.controller;

import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.service.film.FilmService;
import com.enriquers.videorentalstore.service.rental.RentalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoRentalStoreController implements VideoRentalStoreControllerAPI {

  private final FilmService filmService;
  private final RentalService rentalService;

  @Override
  public List<FilmDto> getAllFilms() {
    return filmService.getAllFilms();
  }

  @Override
  public RentalResponseDto rentFilms(RentalRequestDto rentalRequestDto) {
    return rentalService.rentFilms(rentalRequestDto);
  }
}
