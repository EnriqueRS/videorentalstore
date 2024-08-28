package com.enriquers.videorentalstore;

import static com.enriquers.videorentalstore.model.dto.film.FilmTypeEnum.NEW_RELEASE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.enriquers.videorentalstore.domain.Price;
import com.enriquers.videorentalstore.domain.film.Film;
import com.enriquers.videorentalstore.domain.film.FilmType;
import com.enriquers.videorentalstore.model.dto.PriceDto;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.mapper.FilmMapper;
import com.enriquers.videorentalstore.model.dto.mapper.PriceMapper;
import com.enriquers.videorentalstore.repository.FilmRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class MapperTests {

  @Mock
  private FilmRepository filmRepository;
  private PriceMapper priceMapper;
  private FilmMapper filmMapper;

  @BeforeEach
  void setUp() {
    priceMapper = new PriceMapper();
    filmMapper = new FilmMapper(priceMapper);
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFilmMapperToDto() {
    FilmType FilmType = new FilmType();
    FilmType.setName("NEW_RELEASE");
    FilmType.setId(1L);
    Film filmMock = new Film();
    filmMock.setTitle("Matrix 11");
    filmMock.setType(FilmType);

    FilmDto filmDtoExpected = FilmDto.builder()
        .title("Matrix 11")
        .type(NEW_RELEASE)
        .build();

    Mockito.when(filmRepository.findByTitle("Matrix 11"))
        .thenReturn(Optional.of(filmMock));

    FilmDto filmDtoOptional = filmMapper.toDto(filmMock);

    assertNotNull(filmDtoOptional);
    assertEquals(filmDtoOptional, filmDtoExpected);
  }

  @Test
  void testFilmMapperToDtoNull() {
    FilmDto filmDtoOptional = filmMapper.toDto(null);
    assertNull(filmDtoOptional);
  }

  @Test
  void testPriceMapperToDto() {
    Price price = new Price();
    price.setAmount(100L);
    price.setCurrency("EUR");

    PriceDto priceDto = priceMapper.toDto(price);

    assertNotNull(priceDto);
    assertEquals(100, priceDto.getAmount());
    assertEquals("EUR", priceDto.getCurrency());
  }

}
