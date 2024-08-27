package com.enriquers.videorentalstore.service.film;

import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.mapper.FilmMapper;
import com.enriquers.videorentalstore.repository.FilmRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmService {
  private final FilmRepository filmRepository;
  private final FilmMapper filmMapper;

  public List<FilmDto> getAllFilms() {
    return filmRepository.findAll().stream()
        .map(filmMapper::toDto)
        .collect(Collectors.toList());
  }

  public Optional<FilmDto> getFilm(String title) {
    return filmRepository.findByTitle(title)
        .map(filmMapper::toDto);
  }
}
