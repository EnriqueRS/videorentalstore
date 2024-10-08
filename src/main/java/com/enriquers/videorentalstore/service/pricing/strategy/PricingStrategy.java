package com.enriquers.videorentalstore.service.pricing.strategy;

public interface PricingStrategy {
  double calculatePrice(int daysRented);

  double calculateLateCharge(int daysLate);

  int getBonusPoints();
}
