package com.enriquers.videorentalstore.model.dto.rental;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RentalFilmItemDto {
  @NotBlank(message = "Title is mandatory")
  private String title;
  private double rentPrice;
  @Min(value = 1, message = "Days must be greater than 0")
  private int days;
}
