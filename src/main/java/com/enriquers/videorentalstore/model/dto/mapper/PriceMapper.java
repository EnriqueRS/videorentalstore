package com.enriquers.videorentalstore.model.dto.mapper;

import com.enriquers.videorentalstore.domain.Price;
import com.enriquers.videorentalstore.model.dto.PriceDto;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
  public PriceDto toDto(Price price) {
    if (price == null) {
      return null;
    }

    return PriceDto.builder()
        .amount(price.getAmount())
        .currency(price.getCurrency())
        .build();
  }

}
