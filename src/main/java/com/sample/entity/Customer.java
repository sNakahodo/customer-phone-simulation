package com.sample.entity;

public class Customer {

  private int id;
  private String firstName;
  private String lastName;

  public Customer(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String toString() {
    final StringBuilder sb = new StringBuilder("Customer{");
    sb.append("id: ").append(id);
    sb.append(", firstName: ").append(firstName);
    sb.append(", lastName: ").append(lastName);
    sb.append("}");
    return sb.toString();
  }

}
