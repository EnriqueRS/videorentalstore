package com.enriquers.videorentalstore.service.returnfilms;

import com.enriquers.videorentalstore.exceptions.VideoRentalStoreException;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnFilmItemDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnRequestDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnResponseDto;
import com.enriquers.videorentalstore.service.film.FilmService;
import com.enriquers.videorentalstore.service.pricing.factory.PricingStrategyFactory;
import com.enriquers.videorentalstore.service.pricing.strategy.PricingStrategy;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReturnService {

  private final FilmService filmService;

  public ReturnResponseDto returnFilms(ReturnRequestDto returnRequestDto) {
    ReturnResponseDto returnResponseDto = new ReturnResponseDto();

    double totalLateSurcharge = 0;
    for (ReturnFilmItemDto returnFilmItemDto : returnRequestDto.getFilms()) {
      double surcharge = calculateLateChargeFilm(returnFilmItemDto);
      returnFilmItemDto.setSurcharge(surcharge);
      totalLateSurcharge += surcharge;
      returnResponseDto.getFilms().add(returnFilmItemDto);
    }
    returnResponseDto.setTotalLateCharge(totalLateSurcharge);
    return returnResponseDto;
  }

 public double calculateLateChargeFilm(ReturnFilmItemDto returnFilmItemDto) {
    Optional<FilmDto> filmDtoOptional = filmService.getFilm(returnFilmItemDto.getTitle());
    if (filmDtoOptional.isEmpty()) {
      throw new VideoRentalStoreException(VideoRentalStoreException.FILM_NOT_FOUND, returnFilmItemDto.getTitle());
    }
    FilmDto filmDto = filmDtoOptional.get();

    PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(filmDto.getType());
    return pricingStrategy.calculateLateCharge(returnFilmItemDto.getDays());
  }

}
