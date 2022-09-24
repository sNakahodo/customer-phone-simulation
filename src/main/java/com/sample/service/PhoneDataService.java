package com.sample.service;

import com.sample.datasource.PhoneStore;
import com.sample.entity.Phone;
import com.sample.exception.DataException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PhoneDataService implements DataService<Phone> {

  private List<Phone> phoneData;

  public PhoneDataService() {
    phoneData = PhoneStore.load();
  }

  public Phone getById(int id) throws DataException {
    return phoneData.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new DataException("The phone does not exist by requested id: " + id));
  }

  @Override
  public List<Phone> getAll() {
    return phoneData;
  }

  @Override
  public List<Phone> getByRelationId(int customerId) throws DataException {
    List<Phone> phones = phoneData.stream().filter(p -> p.getCustomerId() == customerId).toList();

    if (phones.isEmpty()) {
      throw new DataException("The phone does not exist by requested customer id: " + customerId);
    }

    return phones;
  }

  @Override
  public Phone update(int id, Phone target) throws DataException {
    Optional<Phone> phoneOptional = filterById(id).findFirst();

    if (phoneOptional.isEmpty()) {
      throw new DataException("The requested phone does not exist. Phone id: " + id);
    }

    filterById(id).forEach(p -> {
      p.setPhoneNumber(target.getPhoneNumber());
      p.setCustomerId(target.getCustomerId());
      p.setActive(target.isActive());
    });
    return filterById(id).findFirst().get();
  }

  private Stream<Phone> filterById(int id) {
    return phoneData.stream().filter(p -> p.getId() == id);
  }
}
