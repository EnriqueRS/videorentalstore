package com.enriquers.videorentalstore.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FilmError {
      private String message;

      @JsonInclude(JsonInclude.Include.NON_NULL)
      private List<String> errors;

      public FilmError(String message) {
            this.message = message;
      }
}
