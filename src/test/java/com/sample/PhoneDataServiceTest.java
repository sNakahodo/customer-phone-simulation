package com.sample;

import com.sample.entity.Phone;
import com.sample.exception.DataException;
import com.sample.service.PhoneDataService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneDataServiceTest {

  private PhoneDataService phoneDataService  = new PhoneDataService();

  @Test
  void shouldGetAllPhones() {
    List<Phone> phones = phoneDataService.getAll();
    Assertions.assertEquals(10, phones.size());
  }

  @Test
  void shouldGetPhoneById() throws Exception {
    int id = 1;
    Phone phone = phoneDataService.getById(id);
    Assertions.assertEquals(id, phone.getId());
    Assertions.assertEquals(1, phone.getCustomerId());
    Assertions.assertEquals("0409111222", phone.getPhoneNumber());
  }

  @Test
  void shouldThrowOnGetByByInvalidId() {
    int id = 100;
    DataException exception = Assertions.assertThrows(DataException.class, () -> phoneDataService.getById(id));
    Assertions.assertEquals("The phone does not exist by requested id: " + id, exception.getMessage());
  }

  @Test
  void shouldGetPhoneByCustomerId() throws Exception {
    int customerId = 2;
    List<Phone> phones = phoneDataService.getByRelationId(customerId);
    Assertions.assertEquals(2, phones.size());

    Phone phone1 = phones.get(0);
    Phone phone2 = phones.get(1);

    Assertions.assertEquals(2, phones.size());

    Assertions.assertEquals(2, phone1.getId());
    Assertions.assertEquals(customerId, phone1.getCustomerId());
    Assertions.assertEquals("0409222333", phone1.getPhoneNumber());

    Assertions.assertEquals(3, phone2.getId());
    Assertions.assertEquals(customerId, phone2.getCustomerId());
    Assertions.assertEquals("0409333444", phone2.getPhoneNumber());
  }

  @Test
  void shouldThrowOnInvalidCustomerId() {
    int customerId = 100;
    DataException exception = Assertions.assertThrows(DataException.class, () -> phoneDataService.getByRelationId(customerId));
    Assertions.assertEquals("The phone does not exist by requested customer id: " + customerId, exception.getMessage());
  }

  @Test
  void shouldUpdatePhone() throws Exception {
    int id = 3;
    Phone existingPhone = phoneDataService.getById(id);

    Assertions.assertEquals(id, existingPhone.getId());
    Assertions.assertEquals(2, existingPhone.getCustomerId());
    Assertions.assertEquals("0409333444", existingPhone.getPhoneNumber());
    Assertions.assertFalse(existingPhone.isActive());

    Phone updateTarget = new Phone(id, 3, "0401234567", true);

    phoneDataService.update(id, updateTarget);

    Phone updatedPhone = phoneDataService.getById(id);

    Assertions.assertEquals(id, updatedPhone.getId());
    Assertions.assertEquals(3, updatedPhone.getCustomerId());
    Assertions.assertEquals("0401234567", updatedPhone.getPhoneNumber());
    Assertions.assertTrue(updatedPhone.isActive());
  }

  @Test
  void shouldThrowOnAttemptToUpdateInvalidPhone() {
    int id = 100;
    DataException exception = Assertions.assertThrows(
        DataException.class,
        () -> phoneDataService.update(id, null));

    Assertions.assertEquals("The requested phone does not exist. Phone id: " + id, exception.getMessage());
  }

}
