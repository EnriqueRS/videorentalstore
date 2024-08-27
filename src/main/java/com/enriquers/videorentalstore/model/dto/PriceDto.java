package com.enriquers.videorentalstore.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PriceDto {
  private long amount;
  private String currency;

}
