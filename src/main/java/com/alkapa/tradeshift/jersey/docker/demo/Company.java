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

  private transient int depth;

   /**
   * @return the depth
   */
  public int getDepth() {
    return depth;
  }

  /**
   * @param depth the depth to set
   */
  public void setDepth(int depth) {
    this.depth = depth;
  }

  private int rootId;

    /**
   * @return the rootId
   */
  public int getRootId() {
    return rootId;
  }

  /**
   * @param rootId the rootId to set
   */
  public void setRootId(int rootId) {
    this.rootId = rootId;
  }

  private int height;

  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(int height) {
    this.height = height;
  }

  public Company() {}

  public Company(int id, String name, int parentId, int depth) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
    this.depth = depth;
  }
}