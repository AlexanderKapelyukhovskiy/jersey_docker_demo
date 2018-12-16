package com.alkapa.tradeshift.jersey.docker.demo;

public class Company {
  private int id;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  private String name;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  private int parentId;
  /**
   * @return the parentId
   */
  public int getParentId() {
    return parentId;
  }

  /**
   * @param parentId the parentId to set
   */
  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public Company(int id, String name, int parentId) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
  }
}