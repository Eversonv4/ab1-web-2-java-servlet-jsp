package com.escola.repository;

import com.escola.database.Database;
import com.escola.model.Disciplina;

import java.sql.*;
import java.util.*;

public class DisciplinaRepository {

  public void save(Disciplina d) {

    try (Connection c = Database.getConnection()) {

      if (d.getId() == 0) {

        PreparedStatement ps = c.prepareStatement(
            "INSERT INTO disciplinas(nome) VALUES(?)");

        ps.setString(1, d.getNome());
        ps.executeUpdate();

      } else {

        PreparedStatement ps = c.prepareStatement(
            "UPDATE disciplinas SET nome=? WHERE id=?");

        ps.setString(1, d.getNome());
        ps.setInt(2, d.getId());
        ps.executeUpdate();
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Disciplina> findAll() {

    List<Disciplina> lista = new ArrayList<>();

    try (Connection c = Database.getConnection()) {

      ResultSet rs = c.createStatement()
          .executeQuery("SELECT * FROM disciplinas");

      while (rs.next()) {
        lista.add(new Disciplina(
            rs.getInt("id"),
            rs.getString("nome")));
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return lista;
  }

  public Disciplina findById(int id) {

    try (Connection c = Database.getConnection()) {

      PreparedStatement ps = c.prepareStatement(
          "SELECT * FROM disciplinas WHERE id=?");

      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return new Disciplina(
            rs.getInt("id"),
            rs.getString("nome"));
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return null;
  }

  public void delete(int id) {

    try (Connection c = Database.getConnection()) {

      PreparedStatement ps = c.prepareStatement(
          "DELETE FROM disciplinas WHERE id=?");

      ps.setInt(1, id);
      ps.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}