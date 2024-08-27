package com.enriquers.videorentalstore.model.dto.rental;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResponseDto {

  public RentalResponseDto() {
    this.films = new ArrayList<>();
  }

  private List<RentalFilmItemDto> films;
  private double totalPrice;
}
