package com.enriquers.videorentalstore.service.pricing.factory;

import com.enriquers.videorentalstore.model.dto.film.FilmTypeEnum;
import com.enriquers.videorentalstore.service.pricing.strategy.NewReleasePricingStrategy;
import com.enriquers.videorentalstore.service.pricing.strategy.OldFilmPricingStrategy;
import com.enriquers.videorentalstore.service.pricing.strategy.PricingStrategy;
import com.enriquers.videorentalstore.service.pricing.strategy.RegularFilmPricingStrategy;

public class PricingStrategyFactory {
  public static PricingStrategy getPricingStrategy(FilmTypeEnum type) {
    return switch (type) {
      case NEW_RELEASE -> new NewReleasePricingStrategy();
      case REGULAR_RENTAL -> new RegularFilmPricingStrategy();
      case OLD_FILM -> new OldFilmPricingStrategy();
      default -> throw new IllegalArgumentException("Unknown film type: " + type);
    };
  }
}
