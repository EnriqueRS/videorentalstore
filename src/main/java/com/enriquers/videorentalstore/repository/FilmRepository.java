package com.enriquers.videorentalstore.repository;

import com.enriquers.videorentalstore.domain.film.Film;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
  Optional<Film> findByTitle(String title);
}
