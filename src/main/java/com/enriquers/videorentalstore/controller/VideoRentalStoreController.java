package com.enriquers.videorentalstore.controller;

import com.enriquers.videorentalstore.model.dto.FilmDto;
import com.enriquers.videorentalstore.service.film.FilmService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoRentalStoreController implements VideoRentalStoreControllerAPI {

  private final FilmService filmService;

  @Override
  public List<FilmDto> getAllFilms() {
    return filmService.getAllFilms();
  }
}
