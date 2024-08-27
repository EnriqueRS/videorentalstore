package com.enriquers.videorentalstore.model.dto.returnfilms;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnResponseDto {

  public ReturnResponseDto() {
    this.films = new ArrayList<>();
  }

  private List<ReturnFilmItemDto> films;
  private double totalLateCharge;
}
