package com.sample.datasource;

import com.sample.entity.Customer;
import java.util.List;

public class CustomerStore {

  public static List<Customer> load() {
    return List.of(
        new Customer(1, "John", "Doe"),
        new Customer(2, "Kate", "Millers"),
        new Customer(3, "Mike", "Hawthorn"),
        new Customer(4, "Anna", "Jones"),
        new Customer(5, "Ted", "Murray"),
        new Customer(6, "Mary", "King")
    );
  }

}
