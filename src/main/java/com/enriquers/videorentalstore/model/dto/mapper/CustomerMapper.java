package com.enriquers.videorentalstore.model.dto.mapper;

import com.enriquers.videorentalstore.domain.customer.Customer;
import com.enriquers.videorentalstore.model.dto.customer.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {


  public CustomerDto toDto(Customer customer) {
    if (customer == null) {
      return null;
    }

    return CustomerDto.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .bonusPoints(customer.getBonusPoints())
        .build();
  }

}
