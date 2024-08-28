package com.enriquers.videorentalstore.service.customer;

import com.enriquers.videorentalstore.domain.customer.Customer;
import com.enriquers.videorentalstore.exceptions.VideoRentalStoreException;
import com.enriquers.videorentalstore.model.dto.customer.CustomerDto;
import com.enriquers.videorentalstore.model.dto.mapper.CustomerMapper;
import com.enriquers.videorentalstore.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public List<CustomerDto> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(customerMapper::toDto)
        .collect(Collectors.toList());
  }

  public CustomerDto getCustomer(long customerId) {
    Optional<Customer> customerOptional = customerRepository.findById(customerId);
    if (customerOptional.isEmpty()) {
      throw new VideoRentalStoreException(VideoRentalStoreException.CUSTOMER_NOT_FOUND);
    }
    return customerOptional.map(customerMapper::toDto).get();
  }

  public void addBonusPoints(Long customerId, int bonusPoints) {
    Optional<Customer> customerOptional = customerRepository.findById(customerId);
    if (customerOptional.isEmpty()) {
      throw new VideoRentalStoreException(VideoRentalStoreException.CUSTOMER_NOT_FOUND);
    }
    Customer customer = customerOptional.get();
    customer.setBonusPoints(customer.getBonusPoints() + bonusPoints);
    customerRepository.save(customer);
  }
}
