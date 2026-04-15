package com.escola.service;

import com.escola.model.Aluno;
import com.escola.repository.AlunoRepository;

import java.util.List;

public class AlunoService {

    private AlunoRepository repo = new AlunoRepository();

    public String salvar(Aluno aluno){

        if(aluno.getNome() == null || aluno.getNome().isEmpty()){
            return "Nome é obrigatório";
        }

        if(aluno.getId() == null){
            repo.save(aluno);
        } else {
            repo.update(aluno);
        }

        return null;
    }

    public void deletar(int id){
        repo.delete(id);
    }

    public List<Aluno> listar(){
        return repo.findAll();
    }

    public Aluno buscar(int id){
        return repo.findById(id);
    }
}