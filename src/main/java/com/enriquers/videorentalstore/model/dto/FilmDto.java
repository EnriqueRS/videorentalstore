package com.enriquers.videorentalstore.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilmDto {
  private String title;
  private FilmTypeEnum type;
  private PriceDto price;
}
