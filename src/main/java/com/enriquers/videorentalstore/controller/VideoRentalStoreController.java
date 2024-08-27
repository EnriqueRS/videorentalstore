package com.enriquers.videorentalstore.controller;

import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnRequestDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnResponseDto;
import com.enriquers.videorentalstore.service.film.FilmService;
import com.enriquers.videorentalstore.service.rental.RentalService;
import com.enriquers.videorentalstore.service.returnfilms.ReturnService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoRentalStoreController implements VideoRentalStoreControllerAPI {

  private final FilmService filmService;
  private final RentalService rentalService;
  private final ReturnService returnService;

  @Override
  public List<FilmDto> getAllFilms() {
    return filmService.getAllFilms();
  }

  @Override
  public RentalResponseDto rentFilms(RentalRequestDto rentalRequestDto) {
    return rentalService.rentFilms(rentalRequestDto);
  }

  @Override
  public ReturnResponseDto returnFilms(ReturnRequestDto returnRequestDto) {
    return returnService.returnFilms(returnRequestDto);
  }
}
