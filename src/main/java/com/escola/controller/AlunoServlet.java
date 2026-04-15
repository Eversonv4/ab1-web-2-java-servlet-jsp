package com.escola.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escola.model.Aluno;
import com.escola.repository.AlunoRepository;
import com.escola.service.AlunoService;

@WebServlet("/alunos")
public class AlunoServlet extends HttpServlet {

    private AlunoService service = new AlunoService();

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
        throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action == null){
            listar(req, resp);

        } else if(action.equals("novo")){
            req.getRequestDispatcher("form-aluno.jsp")
               .forward(req, resp);

        } else if(action.equals("editar")){
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("aluno", service.buscar(id));
            req.getRequestDispatcher("form-aluno.jsp")
               .forward(req, resp);

        } else if(action.equals("delete")){
            int id = Integer.parseInt(req.getParameter("id"));
            service.deletar(id);
            resp.sendRedirect("alunos");
        }
    }

    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String nome = req.getParameter("nome");

        Aluno aluno = new Aluno(
            id == null || id.isEmpty() ? null : Integer.parseInt(id),
            nome
        );

        String erro = service.salvar(aluno);

        if(erro != null){
            req.setAttribute("erro", erro);
            req.setAttribute("aluno", aluno);
            req.getRequestDispatcher("form-aluno.jsp")
               .forward(req, resp);
        } else {
            resp.sendRedirect("alunos");
        }
    }

    private void listar(HttpServletRequest req,
                        HttpServletResponse resp)
        throws ServletException, IOException {

        req.setAttribute("lista", service.listar());
        req.getRequestDispatcher("alunos.jsp")
           .forward(req, resp);
    }
}