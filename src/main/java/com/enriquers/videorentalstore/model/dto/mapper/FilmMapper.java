package com.enriquers.videorentalstore.model.dto.mapper;

import com.enriquers.videorentalstore.domain.Film;
import com.enriquers.videorentalstore.model.dto.FilmDto;
import com.enriquers.videorentalstore.model.dto.FilmTypeEnum;
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

//  public Film toEntity(FilmDto filmDto) {
//    if (filmDto == null) {
//      return null;
//    }
//
//    Film film = new Film();
//    film.setTitle(filmDto.getTitle());
//    film.setType(filmDto.getType().name());
//    return film;
//  }

}
