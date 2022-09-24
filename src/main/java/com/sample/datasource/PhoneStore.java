package com.sample.datasource;

import com.sample.entity.Phone;
import java.util.List;

public class PhoneStore {

  public static List<Phone> load() {
    return List.of(
        new Phone(1, 1, "0409111222", true),
        new Phone(2, 2, "0409222333", true),
        new Phone(3, 2, "0409333444", false),
        new Phone(4, 3, "0409333444", true),
        new Phone(5, 3, "0421333444", false),
        new Phone(6, 4, "0421333444", false),
        new Phone(7, 5, "0421333444", true),
        new Phone(8, 6, "0421333444", false),
        new Phone(9, 6, "0421333444", true),
        new Phone(10, 6, "0421333444", false)
    );
  }

}
