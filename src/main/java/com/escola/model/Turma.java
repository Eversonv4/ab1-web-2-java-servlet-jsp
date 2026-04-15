package com.escola.model;

import java.util.List;

public class Turma {

    private int id;
    private String nome;
    private int professorId;
    private List<Aluno> alunos;

    public Turma(int id, String nome, int professorId){
        this.id = id;
        this.nome = nome;
        this.professorId = professorId;
    }

    public int getId(){ return id; }
    public String getNome(){ return nome; }
    public int getProfessorId(){ return professorId; }

    public List<Aluno> getAlunos(){ return alunos; }
    public void setAlunos(List<Aluno> alunos){ this.alunos = alunos; }
}