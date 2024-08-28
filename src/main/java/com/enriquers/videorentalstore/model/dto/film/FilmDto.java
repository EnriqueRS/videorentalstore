package com.enriquers.videorentalstore.model.dto.film;

import com.enriquers.videorentalstore.model.dto.PriceDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FilmDto {
  private String title;
  private FilmTypeEnum type;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PriceDto price;
}
