package com.enriquers.videorentalstore.model.dto.customer;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerDto {
  private Long id;
  private String firstName;
  private String lastName;
  private int bonusPoints;
}
