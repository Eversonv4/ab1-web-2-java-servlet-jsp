package com.escola.repository;

import com.escola.database.Database;
import com.escola.model.Aluno;
import com.escola.model.Turma;

import java.sql.*;
import java.util.*;

public class TurmaRepository {

  public void save(String nome, int professorId, String[] alunos) {

    try (Connection c = Database.getConnection()) {

      c.setAutoCommit(false);

      PreparedStatement ps = c.prepareStatement(
          "INSERT INTO turmas(nome, professor_id) VALUES(?, ?)",
          Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, nome);
      ps.setInt(2, professorId);
      ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();
      rs.next();
      int turmaId = rs.getInt(1);

      if (alunos != null) {
        PreparedStatement rel = c.prepareStatement(
            "INSERT INTO turma_aluno(turma_id, aluno_id) VALUES(?, ?)");

        for (String a : alunos) {
          rel.setInt(1, turmaId);
          rel.setInt(2, Integer.parseInt(a));
          rel.addBatch();
        }

        rel.executeBatch();
      }

      c.commit();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<Map<String, Object>> detalhe(int turmaId) {

    List<Map<String, Object>> lista = new ArrayList<>();

    try (Connection c = Database.getConnection()) {

      PreparedStatement ps = c.prepareStatement(
          "SELECT a.id, a.nome, ta.nota1, ta.nota2 " +
              "FROM alunos a " +
              "JOIN turma_aluno ta ON a.id = ta.aluno_id " +
              "WHERE ta.turma_id = ?");

      ps.setInt(1, turmaId);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Map<String, Object> m = new HashMap<>();
        m.put("id", rs.getInt("id"));
        m.put("nome", rs.getString("nome"));
        m.put("nota1", rs.getObject("nota1"));
        m.put("nota2", rs.getObject("nota2"));
        lista.add(m);
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return lista;
  }

  public List<Turma> findAll() {

    List<Turma> lista = new ArrayList<>();

    try (Connection c = Database.getConnection()) {

      ResultSet rs = c.createStatement()
          .executeQuery("SELECT * FROM turmas");

      while (rs.next()) {
        lista.add(new Turma(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("professor_id")));
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return lista;
  }

  public void updateNota(int turmaId, int alunoId, Double n1, Double n2) {

    try (Connection c = Database.getConnection()) {

      PreparedStatement ps = c.prepareStatement(
          "UPDATE turma_aluno SET nota1=?, nota2=? WHERE turma_id=? AND aluno_id=?");

      ps.setObject(1, n1);
      ps.setObject(2, n2);
      ps.setInt(3, turmaId);
      ps.setInt(4, alunoId);

      ps.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Turma findById(int id) {

    try (Connection c = Database.getConnection()) {

      PreparedStatement ps = c.prepareStatement(
          "SELECT * FROM turmas WHERE id=?");

      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

        Turma t = new Turma(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("professor_id"));

        PreparedStatement ps2 = c.prepareStatement(
            "SELECT aluno_id FROM turma_aluno WHERE turma_id=?");

        ps2.setInt(1, id);

        ResultSet rs2 = ps2.executeQuery();

        List<Aluno> alunos = new ArrayList<>();

        while (rs2.next()) {
          alunos.add(new Aluno(rs2.getInt("aluno_id"), null));
        }

        t.setAlunos(alunos);

        return t;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return null;
  }

  public void update(int id, String nome, int professorId, String[] alunos) {

    try (Connection c = Database.getConnection()) {

      PreparedStatement ps = c.prepareStatement(
          "UPDATE turmas SET nome=?, professor_id=? WHERE id=?");

      ps.setString(1, nome);
      ps.setInt(2, professorId);
      ps.setInt(3, id);
      ps.executeUpdate();

      PreparedStatement del = c.prepareStatement(
          "DELETE FROM turma_aluno WHERE turma_id=?");

      del.setInt(1, id);
      del.executeUpdate();

      if (alunos != null) {

        PreparedStatement ins = c.prepareStatement(
            "INSERT INTO turma_aluno(turma_id, aluno_id) VALUES(?,?)");

        for (String alunoId : alunos) {
          ins.setInt(1, id);
          ins.setInt(2, Integer.parseInt(alunoId));
          ins.executeUpdate();
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(int id) {

    try (Connection c = Database.getConnection()) {

      c.setAutoCommit(false);

      PreparedStatement ps1 = c.prepareStatement(
          "DELETE FROM turma_aluno WHERE turma_id=?");
      ps1.setInt(1, id);
      ps1.executeUpdate();

      PreparedStatement ps2 = c.prepareStatement(
          "DELETE FROM turmas WHERE id=?");
      ps2.setInt(1, id);
      ps2.executeUpdate();

      c.commit();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}