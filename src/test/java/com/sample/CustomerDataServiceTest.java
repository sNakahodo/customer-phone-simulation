package com.sample;

import com.sample.entity.Customer;
import com.sample.exception.DataException;
import com.sample.service.CustomerDataService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerDataServiceTest {

  private CustomerDataService customerDataService  = new CustomerDataService();

  @Test
  void shouldGetAllCustomers() {
    List<Customer> customers = customerDataService.getAll();
    Assertions.assertEquals(6, customers.size());
  }

  @Test
  void shouldThrowOnGetByRelationId() {
    DataException exception = Assertions.assertThrows(DataException.class, () -> customerDataService.getByRelationId(1));
    Assertions.assertEquals("Not implemented yet", exception.getMessage());
  }

  @Test
  void shouldThrowOnUpdate() {
    DataException exception = Assertions.assertThrows(DataException.class, () -> customerDataService.update(1, null));
    Assertions.assertEquals("Not implemented yet", exception.getMessage());
  }

}
