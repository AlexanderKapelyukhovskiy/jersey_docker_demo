package com.alkapa.tradeshift.jersey.docker.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alkapa.tradeshift.jersey.docker.demo.Company;

public class CompaniesStorage {

  private String connectionString;

  public CompaniesStorage(String connectionString) {
    this.connectionString = connectionString;
  }

  public Company[] getChildCompanies(int id) throws SQLException {
    Connection conn = null;
    PreparedStatement st = null;
    try {

      String queryText = "WITH RECURSIVE company_tree AS (\n"
      + "SELECT * FROM Companies WHERE id=?\n"
      + "UNION ALL\n"
      + "SELECT c.* FROM Companies AS c\n"
      + "JOIN company_tree AS t ON c.parent_id = t.id\n" + ")\n"
      + "SELECT * FROM company_tree;";

      conn = DriverManager.getConnection(this.connectionString);
      st = conn.prepareStatement(queryText);
      st.setInt(1, id);

      ResultSet rs = st.executeQuery();
      ArrayList<Company> companies = new ArrayList<Company>();

      while (rs.next()) {
        int companyId = rs.getInt("id");
        String name = rs.getString("name");
        int parentId = rs.getInt("parent_id");

        companies.add(new Company(companyId, name, parentId));
      }
      return companies.toArray(new Company[0]);
    } finally {
      if (st != null) {
        st.close();
      }
      if (conn != null) {
        conn.close();
      }
    }
  }

  public boolean updateCompanyParentId(int id, int parentId) throws SQLException {
    Connection conn = null;
    PreparedStatement st = null;
    try {
      String commandText =
      "UPDATE Companies\n"
      + "SET parent_id = ?\n"
      + "WHERE id = ?;";

      conn = DriverManager.getConnection(this.connectionString);
      st = conn.prepareStatement(commandText);

      st.setInt(1, parentId);
      st.setInt(2, id);

      return st.execute();
    } finally {
      if (st != null) {
        st.close();
      }
      if (conn != null) {
        conn.close();
      }
    }
  }
}