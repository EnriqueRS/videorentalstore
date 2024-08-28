package com.enriquers.videorentalstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.enriquers.videorentalstore.exceptions.VideoRentalStoreException;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.film.FilmTypeEnum;
import com.enriquers.videorentalstore.model.dto.rental.RentalFilmItemDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.service.customer.CustomerService;
import com.enriquers.videorentalstore.service.film.FilmService;
import com.enriquers.videorentalstore.service.pricing.strategy.PricingStrategy;
import com.enriquers.videorentalstore.service.rental.RentalService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RentalServiceTest {

  @Mock
  private FilmService filmService;

  @Mock
  private CustomerService customerService;

  @Mock
  private PricingStrategy pricingStrategy;

  @InjectMocks
  private RentalService rentalService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void givenRentalRequest_whenRentFilms_thenReturnRentalResponse() {
    RentalFilmItemDto rentalFilmItemDto = new RentalFilmItemDto();
    rentalFilmItemDto.setTitle("Matrix 11");
    rentalFilmItemDto.setDays(2);

    RentalRequestDto rentalRequestDto = new RentalRequestDto();
    rentalRequestDto.setFilms(Collections.singletonList(rentalFilmItemDto));

    FilmDto filmDto = new FilmDto();
    filmDto.setTitle("Matrix 11");
    filmDto.setType(FilmTypeEnum.valueOf("NEW_RELEASE"));

    when(filmService.getFilm("Matrix 11")).thenReturn(Optional.of(filmDto));
    when(pricingStrategy.calculatePrice(2)).thenReturn(80.0);

    RentalResponseDto rentalResponseDto = rentalService.rentFilms(rentalRequestDto);

    assertNotNull(rentalResponseDto);
    assertEquals(1, rentalResponseDto.getFilms().size());
    assertEquals(80.0, rentalResponseDto.getTotalPrice());
  }

  @Test
  void givenRentalRequest_whenRentFilms_FilmNotFoundException() {
    RentalFilmItemDto rentalFilmItemDto = new RentalFilmItemDto();
    rentalFilmItemDto.setTitle("Matrix 11");
    rentalFilmItemDto.setDays(2);

    RentalRequestDto rentalRequestDto = new RentalRequestDto();
    rentalRequestDto.setFilms(Collections.singletonList(rentalFilmItemDto));

    when(filmService.getFilm(Mockito.anyString())).thenReturn(Optional.empty());

    assertThrows(VideoRentalStoreException.class, () -> {
      rentalService.rentFilms(rentalRequestDto);
    });
  }

}
