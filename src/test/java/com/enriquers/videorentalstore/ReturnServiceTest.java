package com.enriquers.videorentalstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.enriquers.videorentalstore.exceptions.VideoRentalStoreException;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.film.FilmTypeEnum;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnFilmItemDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnRequestDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnResponseDto;
import com.enriquers.videorentalstore.service.film.FilmService;
import com.enriquers.videorentalstore.service.pricing.strategy.PricingStrategy;
import com.enriquers.videorentalstore.service.returnfilms.ReturnService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReturnServiceTest {

  @Mock
  private FilmService filmService;

  @Mock
  private PricingStrategy pricingStrategy;

  @InjectMocks
  private ReturnService returnService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void givenReturnRequest_whenReturnFilms_thenReturnReturnResponse() {
    ReturnFilmItemDto returnFilmItemDto = new ReturnFilmItemDto();
    returnFilmItemDto.setTitle("Matrix 11");
    returnFilmItemDto.setDaysDelayed(2);

    ReturnRequestDto returnRequestDto = new ReturnRequestDto();
    returnRequestDto.setFilms(Collections.singletonList(returnFilmItemDto));

    FilmDto filmDto = new FilmDto();
    filmDto.setTitle("Matrix 11");
    filmDto.setType(FilmTypeEnum.valueOf("NEW_RELEASE"));

    when(filmService.getFilm("Matrix 11")).thenReturn(Optional.of(filmDto));
    when(pricingStrategy.calculateLateCharge(2)).thenReturn(80.0);

    ReturnResponseDto returnResponseDto = returnService.returnFilms(returnRequestDto);

    assertNotNull(returnResponseDto);
    assertEquals(1, returnResponseDto.getFilms().size());
    assertEquals(80.0, returnResponseDto.getTotalLateCharge());
    assertEquals(80.0, returnResponseDto.getFilms().get(0).getSurcharge());
  }

  @Test
  void givenReturnRequest_whenReturnFilms_FilmNotFoundException() {
    ReturnFilmItemDto returnFilmItemDto = new ReturnFilmItemDto();
    returnFilmItemDto.setTitle("Unknown Film");
    returnFilmItemDto.setDaysDelayed(2);

    when(filmService.getFilm("Unknown Film")).thenReturn(Optional.empty());

    assertThrows(VideoRentalStoreException.class, () -> {
      returnService.calculateLateChargeFilm(returnFilmItemDto);
    });
  }
}
