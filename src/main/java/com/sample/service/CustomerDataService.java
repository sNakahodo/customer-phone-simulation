package com.sample.service;

import com.sample.datasource.CustomerStore;
import com.sample.entity.Customer;
import com.sample.exception.DataException;
import java.util.List;

public class CustomerDataService implements DataService<Customer> {

  private List<Customer> data;

  public CustomerDataService() {
    this.data = CustomerStore.load();
  }

  @Override
  public List<Customer> getAll() {
    return this.data;
  }

  @Override
  public List<Customer> getByRelationId(int relationId) throws DataException {
    // let's pretend this is not implemented yet
    throw new DataException("Not implemented yet");
  }

  @Override
  public Customer update(int id, Customer target) throws DataException {
    // let's pretend this is not implemented yet
    throw new DataException("Not implemented yet");
  }

}
