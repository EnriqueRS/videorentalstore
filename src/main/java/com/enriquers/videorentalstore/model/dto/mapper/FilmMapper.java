package com.enriquers.videorentalstore.model.dto.mapper;

import com.enriquers.videorentalstore.domain.film.Film;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.film.FilmTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilmMapper {

  private final PriceMapper priceMapper;


  public FilmDto toDto(Film film) {
    if (film == null) {
      return null;
    }

    return FilmDto.builder()
        .title(film.getTitle())
        .type(FilmTypeEnum.valueOf(film.getType().getName()))
        .price(priceMapper.toDto(film.getType().getPrice()))
        .build();
  }

}
