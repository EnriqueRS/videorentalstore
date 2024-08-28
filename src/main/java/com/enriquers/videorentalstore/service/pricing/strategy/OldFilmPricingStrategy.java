package com.enriquers.videorentalstore.service.pricing.strategy;

public class OldFilmPricingStrategy implements PricingStrategy {
private static final double BASIC_PRICE = 30.0;
private static final int BONUS_POINTS = 1;

@Override
public double calculatePrice(int daysRented) {
  if (daysRented <= 5) {
    return BASIC_PRICE;
  } else {
    return BASIC_PRICE + (BASIC_PRICE * (daysRented - 5));
  }
}

  @Override
  public double calculateLateCharge(int daysLate) {
    if (daysLate == 0) {
      return 0;
    } else if (daysLate > 0 && daysLate <= 5) {
      return BASIC_PRICE;
    } else {
      return BASIC_PRICE + (BASIC_PRICE * (daysLate - 5));
    }
  }

  @Override
  public int getBonusPoints() {
    return BONUS_POINTS;
  }
}
