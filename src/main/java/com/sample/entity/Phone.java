package com.sample.entity;

public class Phone {

  private int id;
  private int customerId;
  private String phoneNumber;
  private boolean isActive;

  public Phone(int id, int customerId, String phoneNumber, boolean isActive) {
    this.id = id;
    this.customerId = customerId;
    this.phoneNumber = phoneNumber;
    this.isActive = isActive;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public String toString() {
    final StringBuilder sb = new StringBuilder("Phone {");
    sb.append("id: ").append(id);
    sb.append(", customerId: ").append(customerId);
    sb.append(", phoneNumber: ").append(phoneNumber);
    sb.append(", isActive: ").append(isActive);
    sb.append("}");
    return sb.toString();
  }

}
