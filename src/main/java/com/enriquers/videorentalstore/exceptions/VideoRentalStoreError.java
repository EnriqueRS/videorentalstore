package com.enriquers.videorentalstore.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VideoRentalStoreError {
      private String message;

      @JsonInclude(JsonInclude.Include.NON_NULL)
      private List<String> errors;

      public VideoRentalStoreError(String message) {
            this.message = message;
      }
}
