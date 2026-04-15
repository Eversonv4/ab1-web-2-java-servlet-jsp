package com.escola.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.escola.repository.*;

@WebServlet("/turmas")
public class TurmaServlet extends HttpServlet {

  private TurmaRepository repo = new TurmaRepository();
  private ProfessorRepository professorRepository = new ProfessorRepository();
  private AlunoRepository alunoRepository = new AlunoRepository();

  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    String action = req.getParameter("action");

    if (action == null) {
      req.setAttribute("lista", repo.findAll());
      req.getRequestDispatcher("turmas.jsp").forward(req, resp);
      return;
    }

    if ("nota".equals(action)) {

      int turmaId = Integer.parseInt(req.getParameter("turmaId"));
      int alunoId = Integer.parseInt(req.getParameter("alunoId"));

      Double n1 = req.getParameter("nota1").isEmpty() ? null : Double.parseDouble(req.getParameter("nota1"));
      Double n2 = req.getParameter("nota2").isEmpty() ? null : Double.parseDouble(req.getParameter("nota2"));

      repo.updateNota(turmaId, alunoId, n1, n2);

      resp.sendRedirect("turmas?action=detalhe&id=" + turmaId);
    }

    if ("novo".equals(action)) {

      req.setAttribute("professores", professorRepository.findAll());
      req.setAttribute("alunos", alunoRepository.findAll());

      req.getRequestDispatcher("form-turma.jsp")
          .forward(req, resp);
    }

    if ("editar".equals(action)) {

      int id = Integer.parseInt(req.getParameter("id"));

      req.setAttribute("turma", repo.findById(id));
      req.setAttribute("professores", professorRepository.findAll());
      req.setAttribute("alunos", alunoRepository.findAll());

      req.getRequestDispatcher("form-turma.jsp")
          .forward(req, resp);
    }

    if ("detalhe".equals(action)) {

      int id = Integer.parseInt(req.getParameter("id"));

      req.setAttribute("alunos", repo.detalhe(id));
      req.setAttribute("turmaId", id);

      req.getRequestDispatcher("turma-detalhe.jsp")
          .forward(req, resp);
    }

    if ("delete".equals(action)) {
      int id = Integer.parseInt(req.getParameter("id"));
      repo.delete(id);
      resp.sendRedirect("turmas");
    }
  }

  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws IOException {

    req.setCharacterEncoding("UTF-8");

    String action = req.getParameter("action");

    if ("nota".equals(action)) {

      int turmaId = Integer.parseInt(req.getParameter("turmaId"));
      int alunoId = Integer.parseInt(req.getParameter("alunoId"));

      String n1Str = req.getParameter("nota1");
      String n2Str = req.getParameter("nota2");

      Double n1 = (n1Str == null || n1Str.isEmpty()) ? null : Double.parseDouble(n1Str);
      Double n2 = (n2Str == null || n2Str.isEmpty()) ? null : Double.parseDouble(n2Str);

      repo.updateNota(turmaId, alunoId, n1, n2);

      resp.sendRedirect("turmas?action=detalhe&id=" + turmaId);
      return;
    }

    String idParam = req.getParameter("id");
    String nome = req.getParameter("nome");
    int professor = Integer.parseInt(req.getParameter("professor_id"));
    String[] alunos = req.getParameterValues("alunos");

    if (idParam == null || idParam.isEmpty()) {
      repo.save(nome, professor, alunos);
    } else {
      int id = Integer.parseInt(idParam);
      repo.update(id, nome, professor, alunos);
    }

    resp.sendRedirect("turmas");
  }
}