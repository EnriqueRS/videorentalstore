package com.enriquers.videorentalstore.model.dto.rental;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RentalFilmItemDto {
  @NotBlank(message = "Title is mandatory")
  private String title;
  private double RentPrice;
  @Min(value = 1, message = "Days must be greater than 0")
  private int days;
}
