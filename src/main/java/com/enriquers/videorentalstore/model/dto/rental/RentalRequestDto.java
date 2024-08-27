package com.enriquers.videorentalstore.model.dto.rental;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequestDto {
  @NotNull(message = "Films must not be null")
  @Valid
  private List<RentalFilmItemDto> films;
}
