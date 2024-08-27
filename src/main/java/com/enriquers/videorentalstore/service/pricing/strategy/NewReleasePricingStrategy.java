package com.enriquers.videorentalstore.service.pricing.strategy;

public class NewReleasePricingStrategy implements PricingStrategy {
  private static final double PREMIUM_PRICE = 40.0;

  @Override
  public double calculatePrice(int daysRented) {
    return PREMIUM_PRICE * daysRented;
  }

  @Override
  public double calculateLateCharge(int daysLate) {
    return PREMIUM_PRICE * daysLate;
  }
}
