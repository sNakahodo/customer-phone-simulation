package com.sample;

import com.sample.service.CustomerDataService;
import com.sample.service.PhoneDataService;

public class Main {

  public static void main(String[] args) {
    Application app = new Application(new PhoneDataService(), new CustomerDataService());
    app.promptActions();
  }

}
