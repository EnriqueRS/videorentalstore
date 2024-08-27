package com.enriquers.videorentalstore.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FILMS")
@Getter
@Setter
public class Film {
  @Id
  private Long id;

  private String title;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private FilmType type;
}
