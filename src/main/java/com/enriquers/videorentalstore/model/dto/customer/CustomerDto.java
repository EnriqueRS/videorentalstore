package com.enriquers.videorentalstore.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerDto {
  private Long id;
  private String firstName;
  private String lastName;
  private int bonusPoints;
}
