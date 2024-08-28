package com.enriquers.videorentalstore.service.rental;

import com.enriquers.videorentalstore.exceptions.VideoRentalStoreException;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.film.FilmTypeEnum;
import com.enriquers.videorentalstore.model.dto.rental.RentalFilmItemDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.service.customer.CustomerService;
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
  private final CustomerService customerService;

  public RentalResponseDto rentFilms(RentalRequestDto rentalRequestDto) {
    RentalResponseDto rentalResponseDto = new RentalResponseDto();

    double totalPrice = 0;
    for (RentalFilmItemDto rentalFilmItemDto : rentalRequestDto.getFilms()) {
      FilmDto filmDto = getFilm(rentalFilmItemDto);
      double price = calculateRentFilmPrice(filmDto, rentalFilmItemDto.getDays());
      rentalFilmItemDto.setRentPrice(price);
      totalPrice += price;
      rentalResponseDto.getFilms().add(rentalFilmItemDto);
      addBonusPoints(rentalRequestDto.getCustomerId(), filmDto.getType());
    }
    rentalResponseDto.setTotalPrice(totalPrice);
    return rentalResponseDto;
  }

  private FilmDto getFilm(RentalFilmItemDto rentalFilmItemDto) {
    Optional<FilmDto> filmDtoOptional = filmService.getFilm(rentalFilmItemDto.getTitle());
    if (filmDtoOptional.isEmpty()) {
      throw new VideoRentalStoreException(VideoRentalStoreException.FILM_NOT_FOUND, rentalFilmItemDto.getTitle());
    }
    return filmDtoOptional.get();
  }

  private void addBonusPoints(Long customerId, FilmTypeEnum filmType) {
    PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(filmType);
    int bonusPoints = pricingStrategy.getBonusPoints();
    customerService.addBonusPoints(customerId, bonusPoints);
  }

  public double calculateRentFilmPrice(FilmDto filmDto,  int days) {
    PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(filmDto.getType());
    return pricingStrategy.calculatePrice(days);
  }

}
