package com.enriquers.videorentalstore.repository;

import com.enriquers.videorentalstore.domain.Film;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
  List<Film> findAll();
}
