package com.enriquers.videorentalstore.model.dto.rental;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RentalRequestDto {
  private List<RentalFilmItemDto> films;
}
