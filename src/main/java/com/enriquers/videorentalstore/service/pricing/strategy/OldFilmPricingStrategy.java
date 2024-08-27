package com.enriquers.videorentalstore.service.pricing.strategy;

public class OldFilmPricingStrategy implements PricingStrategy {
private static final double BASIC_PRICE = 30.0;

@Override
public double calculatePrice(int daysRented) {
  if (daysRented <= 5) {
    return BASIC_PRICE;
  } else {
    return BASIC_PRICE + (BASIC_PRICE * (daysRented - 5));
  }
}
}
