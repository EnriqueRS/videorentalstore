package com.enriquers.videorentalstore.model.dto.returnfilms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReturnFilmItemDto {
  @NotBlank(message = "Title is mandatory")
  private String title;
  private double surcharge;
  @Min(value = 0, message = "Days must be greater than or equal to 0")
  private int daysDelayed;
}
