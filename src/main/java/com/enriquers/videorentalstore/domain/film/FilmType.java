package com.enriquers.videorentalstore.domain.film;

import com.enriquers.videorentalstore.domain.Price;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TYPES")
@Getter
@Setter
public class FilmType {
  @Id
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "price_id")
  private Price price;
}
