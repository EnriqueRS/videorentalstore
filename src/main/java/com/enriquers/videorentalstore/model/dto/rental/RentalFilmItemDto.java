package com.enriquers.videorentalstore.model.dto.rental;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RentalFilmItemDto {
  private String title;
  private double RentPrice;
  private int days;
}
