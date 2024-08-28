package com.enriquers.videorentalstore;

import static org.assertj.core.api.Assertions.assertThat;

import com.enriquers.videorentalstore.controller.VideoRentalStoreControllerAPI;
import com.enriquers.videorentalstore.model.dto.customer.CustomerDto;
import com.enriquers.videorentalstore.model.dto.film.FilmDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalRequestDto;
import com.enriquers.videorentalstore.model.dto.rental.RentalResponseDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnRequestDto;
import com.enriquers.videorentalstore.model.dto.returnfilms.ReturnResponseDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class VideoRentalStoreIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;
  private final HelperTest helperTest = new HelperTest();

  @Test
  void givenFilmsEndpoint_whenGetAllFilms_thenReturnListOfFilms() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.FILMS_URL;
    List<FilmDto> filmsExpected = helperTest.getFilmsExpected();

    ResponseEntity<FilmDto[]> response = restTemplate.getForEntity(url, FilmDto[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotEmpty();
    assertThat(response.getBody()).containsExactlyInAnyOrderElementsOf(filmsExpected);
  }

  @Test
  void givenCustomersEndpoint_whenGetAllCustomers_thenReturnListOfCustomers() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.CUSTOMERS_URL;
    List<CustomerDto> customersExpected = helperTest.getCustomersExpected();

    ResponseEntity<CustomerDto[]> response = restTemplate.getForEntity(url, CustomerDto[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isNotEmpty();
    assertThat(response.getBody()).containsExactlyInAnyOrderElementsOf(customersExpected);
  }

  @Test
  void givenCustomerEndpoint_whenGetCustomer_thenReturnCustomer() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.CUSTOMERS_URL
        + VideoRentalStoreControllerAPI.GET_CUSTOMER_URL.replace("{customerId}", "1");
    CustomerDto customerExpected = helperTest.getCustomerIdExpected();

    ResponseEntity<CustomerDto> response = restTemplate.getForEntity(url, CustomerDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isEqualTo(customerExpected);
  }

  @Test
  void givenRentalEndpoint_whenRentFilms_thenReturnRentalResponse() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.RENTAL_URL;
    RentalRequestDto rentalRequest = helperTest.getRentRequest();
    RentalResponseDto rentalResponseExpected = helperTest.getRentalResponse();

    ResponseEntity<RentalResponseDto> response = restTemplate.postForEntity(url, rentalRequest, RentalResponseDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getTotalPrice()).isEqualTo(rentalResponseExpected.getTotalPrice());
    assertThat(response.getBody().getFilms().size()).isEqualTo(rentalResponseExpected.getFilms().size());
    assertThat(response.getBody().getFilms()).containsExactlyInAnyOrderElementsOf(rentalResponseExpected.getFilms());
  }

  @Test
  void givenReturnEndpoint_whenReturnFilms_thenReturnReturnResponse() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.RETURN_URL;
    ReturnRequestDto returnRequest = helperTest.getReturnRequest();
    ReturnResponseDto returnResponseExpected = helperTest.getReturnResponse();

    ResponseEntity<ReturnResponseDto> response = restTemplate.postForEntity(url, returnRequest, ReturnResponseDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getTotalLateCharge()).isEqualTo(returnResponseExpected.getTotalLateCharge());
    assertThat(response.getBody().getFilms().size()).isEqualTo(returnResponseExpected.getFilms().size());
    assertThat(response.getBody().getFilms()).containsExactlyInAnyOrderElementsOf(returnResponseExpected.getFilms());
  }

  @Test
  void givenRentalEndpoint_whenRentFilmsWithNegativeDay_thenReturnBadRequest() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.RENTAL_URL;
    RentalRequestDto rentalRequest = helperTest.getRentRequest();
    rentalRequest.getFilms().get(0).setDays(-1);

    ResponseEntity<RentalResponseDto> response = restTemplate.postForEntity(url, rentalRequest, RentalResponseDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void givenReturnEndpoint_whenReturnFilmsWithNullFilms_thenReturnBadRequest() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.RETURN_URL;
    ReturnRequestDto returnRequest = helperTest.getReturnRequest();
    returnRequest.setFilms(null);

    ResponseEntity<ReturnResponseDto> response = restTemplate.postForEntity(url, returnRequest, ReturnResponseDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void givenRentalEndpoint_whenRentFilms_thenReturnRentalResponseAndCheckBonusPoints() {
    String url = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.RENTAL_URL;
    RentalRequestDto rentalRequest = helperTest.getRentRequest();
    RentalResponseDto rentalResponseExpected = helperTest.getRentalResponse();

    ResponseEntity<RentalResponseDto> response = restTemplate.postForEntity(url, rentalRequest, RentalResponseDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getTotalPrice()).isEqualTo(rentalResponseExpected.getTotalPrice());
    assertThat(response.getBody().getFilms().size()).isEqualTo(rentalResponseExpected.getFilms().size());
    assertThat(response.getBody().getFilms()).containsExactlyInAnyOrderElementsOf(rentalResponseExpected.getFilms());

    String urlCustomer = VideoRentalStoreControllerAPI.BASE_URL + VideoRentalStoreControllerAPI.CUSTOMERS_URL
        + VideoRentalStoreControllerAPI.GET_CUSTOMER_URL.replace("{customerId}", "1");
    CustomerDto customerExpected = helperTest.getCustomerIdExpected();

    ResponseEntity<CustomerDto> responseCustomer = restTemplate.getForEntity(urlCustomer, CustomerDto.class);

    assertThat(responseCustomer.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseCustomer.getBody()).isNotNull();
    assertThat(responseCustomer.getBody().getBonusPoints()).isEqualTo(5);
  }

}
