package com.enriquers.videorentalstore.service.pricing.strategy;

public class RegularFilmPricingStrategy implements PricingStrategy {
  private static final double BASIC_PRICE = 30.0;

  @Override
  public double calculatePrice(int daysRented) {
    if (daysRented <= 3) {
      return BASIC_PRICE;
    } else {
      return BASIC_PRICE + (BASIC_PRICE * (daysRented - 3));
    }
  }

  @Override
  public double calculateLateCharge(int daysLate) {
    if (daysLate <= 3) {
      return BASIC_PRICE;
    } else {
      return BASIC_PRICE + (BASIC_PRICE * (daysLate - 3));
    }
  }
}
