package com.enriquers.videorentalstore.service.pricing.strategy;

public class NewReleasePricingStrategy implements PricingStrategy {
  private static final double PREMIUM_PRICE = 40.0;
  private static final int BONUS_POINTS = 2;

  @Override
  public double calculatePrice(int daysRented) {
    return PREMIUM_PRICE * daysRented;
  }

  @Override
  public double calculateLateCharge(int daysLate) {
    return PREMIUM_PRICE * daysLate;
  }

  @Override
  public int getBonusPoints() {
    return BONUS_POINTS;
  }
}
