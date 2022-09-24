package com.sample;

import com.sample.service.CustomerDataService;
import com.sample.service.PhoneDataService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeEach
  void setupEach() {
    System.setOut(new PrintStream(outputStream));
  }

  @AfterEach
  void afterEach() {
    //set IO back to normal for further processes in case there is any
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  void shouldSetAction() {
    System.setIn(new ByteArrayInputStream("getAllPhones".getBytes()));
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    String action = application.getAction();
    Assertions.assertEquals("What do you want to do? Choose from: getAllPhones, getAllCustomers, getPhoneByCustomerId, activatePhone, exit\n", outputStream.toString());
    Assertions.assertEquals("getAllPhones", action);
  }

  @Test
  void shouldPromptErrorAndSuggestActionAgain() {
    System.setIn(new ByteArrayInputStream("foo".getBytes()));
    Application application = new Application(new PhoneDataService(), new CustomerDataService());

    //getAction is recursive, accept it to fail as this test does not provide further input.
    Assertions.assertThrows(NoSuchElementException.class, () -> application.getAction());

    Assertions.assertEquals(
        "What do you want to do? Choose from: getAllPhones, getAllCustomers, getPhoneByCustomerId, activatePhone, exit\n"
        + "The selected option is not available. Please choose again\n"
        + "What do you want to do? Choose from: getAllPhones, getAllCustomers, getPhoneByCustomerId, activatePhone, exit\n", outputStream.toString());
  }

  @Test
  void shouldExecuteGetAllPhones() {
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("getAllPhones");

    Assertions.assertTrue(doesContinue);
    Assertions.assertEquals(
        "Phone {id: 1, customerId: 1, phoneNumber: 0409111222, isActive: true}\n"
            + "Phone {id: 2, customerId: 2, phoneNumber: 0409222333, isActive: true}\n"
            + "Phone {id: 3, customerId: 2, phoneNumber: 0409333444, isActive: false}\n"
            + "Phone {id: 4, customerId: 3, phoneNumber: 0409333444, isActive: true}\n"
            + "Phone {id: 5, customerId: 3, phoneNumber: 0421333444, isActive: false}\n"
            + "Phone {id: 6, customerId: 4, phoneNumber: 0421333444, isActive: false}\n"
            + "Phone {id: 7, customerId: 5, phoneNumber: 0421333444, isActive: true}\n"
            + "Phone {id: 8, customerId: 6, phoneNumber: 0421333444, isActive: false}\n"
            + "Phone {id: 9, customerId: 6, phoneNumber: 0421333444, isActive: true}\n"
            + "Phone {id: 10, customerId: 6, phoneNumber: 0421333444, isActive: false}\n", outputStream.toString());
  }

  @Test
  void shouldExecuteGetAllCustomers() {
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("getAllCustomers");

    Assertions.assertTrue(doesContinue);
    Assertions.assertEquals(
        "Customer{id: 1, firstName: John, lastName: Doe}\n"
            + "Customer{id: 2, firstName: Kate, lastName: Millers}\n"
            + "Customer{id: 3, firstName: Mike, lastName: Hawthorn}\n"
            + "Customer{id: 4, firstName: Anna, lastName: Jones}\n"
            + "Customer{id: 5, firstName: Ted, lastName: Murray}\n"
            + "Customer{id: 6, firstName: Mary, lastName: King}\n", outputStream.toString());
  }

  @Test
  void shouldExecuteGetPhoneByCustomerId() {
    System.setIn(new ByteArrayInputStream("2".getBytes()));
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("getPhoneByCustomerId");

    Assertions.assertTrue(doesContinue);
    Assertions.assertEquals(
        "Enter customer id\n"
            + "Phone {id: 2, customerId: 2, phoneNumber: 0409222333, isActive: true}\n"
            + "Phone {id: 3, customerId: 2, phoneNumber: 0409333444, isActive: false}\n", outputStream.toString());
  }

  @Test
  void shouldExecuteGetPhoneByInvalidCustomerIdAndShowError() {
    System.setIn(new ByteArrayInputStream("100".getBytes()));
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("getPhoneByCustomerId");

    Assertions.assertTrue(doesContinue);
    Assertions.assertEquals(
        "Enter customer id\n"
            + "The phone does not exist by requested customer id: 100\n", outputStream.toString());
  }

  @Test
  void shouldActivatePhone() {
    System.setIn(new ByteArrayInputStream("3".getBytes()));
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("activatePhone");

    Assertions.assertTrue(doesContinue);
    Assertions.assertEquals(
        "Enter phone id to activate\n"
            + "Activation complete for phone: Phone {id: 3, customerId: 2, phoneNumber: 0409333444, isActive: true}\n", outputStream.toString());
  }

  @Test
  void shouldAttemptActivateInvalidPhoneAndShowError() {
    System.setIn(new ByteArrayInputStream("100".getBytes()));
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("activatePhone");
    Assertions.assertTrue(doesContinue);
    Assertions.assertEquals(
        "Enter phone id to activate\n"
            + "The phone does not exist by requested id: 100\n", outputStream.toString());
  }

  @Test
  void shouldExitApp() {
    Application application = new Application(new PhoneDataService(), new CustomerDataService());
    boolean doesContinue = application.executeAction("exit");

    Assertions.assertFalse(doesContinue);
    Assertions.assertEquals("Bye\n", outputStream.toString());
  }

}
