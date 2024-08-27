package com.enriquers.videorentalstore.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
public class Price {
  @Id
  private Long id;
  private String name;
  private Long amount;
  private String currency;
}
