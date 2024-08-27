package com.enriquers.videorentalstore.model.dto.film;

import com.enriquers.videorentalstore.model.dto.PriceDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilmDto {
  private String title;
  private FilmTypeEnum type;
  private PriceDto price;
}
