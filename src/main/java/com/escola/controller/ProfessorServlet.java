package com.escola.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import com.escola.model.Professor;
import com.escola.repository.ProfessorRepository;

@WebServlet("/professores")
public class ProfessorServlet extends HttpServlet {

  private ProfessorRepository repo = new ProfessorRepository();

  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    String action = req.getParameter("action");

    if (action == null) {
      req.setAttribute("lista", repo.findAll());
      req.getRequestDispatcher("professores.jsp")
          .forward(req, resp);
      return;
    }

    if ("novo".equals(action)) {
      req.getRequestDispatcher("form-professor.jsp")
          .forward(req, resp);
    }

    if ("editar".equals(action)) {
      int id = Integer.parseInt(req.getParameter("id"));
      req.setAttribute("professor", repo.findById(id));
      req.getRequestDispatcher("form-professor.jsp")
          .forward(req, resp);
    }

    if ("delete".equals(action)) {
      int id = Integer.parseInt(req.getParameter("id"));
      repo.delete(id);
      resp.sendRedirect("professores");
    }
  }

  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws IOException {

    String nome = req.getParameter("nome");
    String idStr = req.getParameter("id");

    Professor p = new Professor(
        idStr == null || idStr.isEmpty() ? 0 : Integer.parseInt(idStr),
        nome);

    repo.save(p);

    resp.sendRedirect("professores");
  }
}