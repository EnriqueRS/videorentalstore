package com.enriquers.videorentalstore.service.rental;

import com.enriquers.videorentalstore.exceptions.FilmException;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalFilmItemDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.service.film.FilmService;
import com.enriquers.videorentalstore.service.pricing.factory.PricingStrategyFactory;
import com.enriquers.videorentalstore.service.pricing.strategy.PricingStrategy;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

  private final FilmService filmService;

  public RentalResponseDto rentFilms(RentalRequestDto rentalRequestDto) {
    RentalResponseDto rentalResponseDto = new RentalResponseDto();

    double totalPrice = 0;
    for (RentalFilmItemDto rentalFilmItemDto : rentalRequestDto.getFilms()) {
      double price = calculateRentFilmPrice(rentalFilmItemDto);
      rentalFilmItemDto.setRentPrice(price);
      totalPrice += price;
      rentalResponseDto.getFilms().add(rentalFilmItemDto);
    }
    rentalResponseDto.setTotalPrice(totalPrice);
    return rentalResponseDto;
  }

  public double calculateRentFilmPrice(RentalFilmItemDto rentalFilmItemDto) {
    Optional<FilmDto> filmDtoOptional = filmService.getFilm(rentalFilmItemDto.getTitle());
    if (filmDtoOptional.isEmpty()) {
      throw new FilmException(FilmException.FILM_NOT_FOUND, rentalFilmItemDto.getTitle());
    }
    FilmDto filmDto = filmDtoOptional.get();

    PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(filmDto.getType());
    return pricingStrategy.calculatePrice(rentalFilmItemDto.getDays());
  }

}
