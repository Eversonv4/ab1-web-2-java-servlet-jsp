package com.escola.database;

import java.sql.Connection;
import java.sql.Statement;

public class InitDB {

    public static void init() {
        try(Connection c = Database.getConnection()) {

            Statement s = c.createStatement();

            s.execute("CREATE TABLE IF NOT EXISTS professores (id INTEGER PRIMARY KEY, nome TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS alunos (id INTEGER PRIMARY KEY, nome TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS disciplinas (id INTEGER PRIMARY KEY, nome TEXT)");

            s.execute("CREATE TABLE IF NOT EXISTS turmas (" +
                    "id INTEGER PRIMARY KEY," +
                    "nome TEXT," +
                    "professor_id INTEGER)");

            s.execute("CREATE TABLE IF NOT EXISTS turma_aluno (" +
                    "id INTEGER PRIMARY KEY," +
                    "turma_id INTEGER," +
                    "aluno_id INTEGER," +
                    "nota1 REAL," +
                    "nota2 REAL)");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}