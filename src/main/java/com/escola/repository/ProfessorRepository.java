package com.escola.repository;

import com.escola.database.Database;
import com.escola.model.Professor;

import java.sql.*;
import java.util.*;

public class ProfessorRepository {

    public void save(Professor p){
        try(Connection c = Database.getConnection()){

            if(p.getId() == 0){
                PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO professores(nome) VALUES(?)"
                );
                ps.setString(1, p.getNome());
                ps.executeUpdate();
            } else {
                PreparedStatement ps = c.prepareStatement(
                    "UPDATE professores SET nome=? WHERE id=?"
                );
                ps.setString(1, p.getNome());
                ps.setInt(2, p.getId());
                ps.executeUpdate();
            }

        } catch(Exception e){ throw new RuntimeException(e); }
    }

    public List<Professor> findAll(){
        List<Professor> lista = new ArrayList<>();

        try(Connection c = Database.getConnection()){

            ResultSet rs = c.createStatement()
                .executeQuery("SELECT * FROM professores");

            while(rs.next()){
                lista.add(new Professor(
                    rs.getInt("id"),
                    rs.getString("nome")
                ));
            }

        } catch(Exception e){ throw new RuntimeException(e); }

        return lista;
    }

    public Professor findById(int id){
        try(Connection c = Database.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM professores WHERE id=?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Professor(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
            }

        } catch(Exception e){ throw new RuntimeException(e); }

        return null;
    }

    public void delete(int id){
        try(Connection c = Database.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                "DELETE FROM professores WHERE id=?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch(Exception e){ throw new RuntimeException(e); }
    }
}