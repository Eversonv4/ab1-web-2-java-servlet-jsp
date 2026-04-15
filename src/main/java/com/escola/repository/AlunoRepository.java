package com.escola.repository;

import com.escola.database.Database;
import com.escola.model.Aluno;

import java.sql.*;
import java.util.*;

public class AlunoRepository {

    public void save(Aluno aluno){
        try(Connection c = Database.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                "INSERT INTO alunos(nome) VALUES(?)"
            );

            ps.setString(1, aluno.getNome());
            ps.executeUpdate();

        } catch(Exception e){ e.printStackTrace(); }
    }

    public void update(Aluno aluno){
        try(Connection c = Database.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                "UPDATE alunos SET nome=? WHERE id=?"
            );

            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getId());

            ps.executeUpdate();

        } catch(Exception e){ e.printStackTrace(); }
    }

    public void delete(int id){
        try(Connection c = Database.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                "DELETE FROM alunos WHERE id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch(Exception e){ e.printStackTrace(); }
    }

    public List<Aluno> findAll(){
        List<Aluno> lista = new ArrayList<>();

        try(Connection c = Database.getConnection()){

            ResultSet rs = c.createStatement()
                .executeQuery("SELECT * FROM alunos");

            while(rs.next()){
                lista.add(new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome")
                ));
            }

        } catch(Exception e){ e.printStackTrace(); }

        return lista;
    }

    public Aluno findById(int id){
        try(Connection c = Database.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM alunos WHERE id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome")
                );
            }

        } catch(Exception e){ e.printStackTrace(); }

        return null;
    }
}