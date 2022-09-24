package com.sample;

import com.sample.entity.Customer;
import com.sample.entity.Phone;
import com.sample.exception.DataException;
import com.sample.service.CustomerDataService;
import com.sample.service.PhoneDataService;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {

  private static final String OPTION_GET_ALL_PHONES = "getAllPhones";
  private static final String OPTION_GET_ALL_CUSTOMERS = "getAllCustomers";
  private static final String OPTION_GET_PHONE_BY_CUSTOMER_ID = "getPhoneByCustomerId";
  private static final String OPTION_ACTIVATE_PHONE = "activatePhone";
  private static final String OPTION_EXIT = "exit";
  private PhoneDataService phoneDataService;
  private CustomerDataService customerDataService;
  private Scanner scanner;

  public Application(PhoneDataService phoneDataService, CustomerDataService customerDataService) {
    this.scanner = new Scanner(System.in);
    this.phoneDataService = phoneDataService;
    this.customerDataService = customerDataService;
  }

  public void promptActions() {
    if (executeAction(getAction())) {
      promptActions();
    }
  }

  public String getAction() {
    List<String> options =  List.of(OPTION_GET_ALL_PHONES, OPTION_GET_ALL_CUSTOMERS, OPTION_GET_PHONE_BY_CUSTOMER_ID, OPTION_ACTIVATE_PHONE, OPTION_EXIT);

    System.out.println("What do you want to do? Choose from: " + String.join(", ", options));
    String action = scanner.nextLine();

    if (!options.contains(action)) {
      System.out.println("The selected option is not available. Please choose again");
      action = getAction();
    }

    return action;
  }

  public boolean executeAction(String action) {
    switch (action) {
      case OPTION_GET_ALL_PHONES:
        getAllPhonesOutput();
        return true;
      case OPTION_GET_ALL_CUSTOMERS:
        getAllCustomersOutput();
        return true;
      case OPTION_GET_PHONE_BY_CUSTOMER_ID:
        System.out.println("Enter customer id");
        getPhoneByCustomerId(Integer.parseInt(scanner.nextLine()));
        return true;
      case OPTION_ACTIVATE_PHONE:
        System.out.println("Enter phone id to activate");
        activatePhone(Integer.parseInt(scanner.nextLine()));
        return true;
      case OPTION_EXIT:
        scanner.close();
        System.out.println("Bye");
        return false;
      default:
        return true;
    }
  }

  private void getAllPhonesOutput() {
    System.out.println(phoneDataService.getAll().stream().map(Phone::toString).collect(
        Collectors.joining("\n")));
  }

  private void getAllCustomersOutput() {
    System.out.println(customerDataService.getAll().stream().map(Customer::toString).collect(
        Collectors.joining("\n")));
  }

  private void getPhoneByCustomerId(int customerId) {
    List<Phone> phones;

    try {
      phones = phoneDataService.getByRelationId(customerId);
      System.out.println(phones.stream().map(Phone::toString).collect(
          Collectors.joining("\n")));
    } catch (DataException e) {
      System.out.println(e.getMessage());
    }
  }

  private void activatePhone(int phoneId) {
    try {
      Phone phone = phoneDataService.getById(phoneId);
      phone.setActive(true);
      Phone updatedPhone = phoneDataService.update(phoneId, phone);
      System.out.println("Activation complete for phone: " + updatedPhone.toString());
    } catch (DataException e) {
      System.out.println(e.getMessage());
    }
  }
}
