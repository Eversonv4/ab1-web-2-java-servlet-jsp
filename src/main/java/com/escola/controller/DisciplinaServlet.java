package com.escola.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import com.escola.model.Disciplina;
import com.escola.repository.DisciplinaRepository;

@WebServlet("/disciplinas")
public class DisciplinaServlet extends HttpServlet {

  private DisciplinaRepository repo = new DisciplinaRepository();

  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    String action = req.getParameter("action");

    if (action == null) {
      req.setAttribute("lista", repo.findAll());
      req.getRequestDispatcher("disciplinas.jsp")
          .forward(req, resp);
      return;
    }

    if ("novo".equals(action)) {
      req.getRequestDispatcher("form-disciplina.jsp")
          .forward(req, resp);
    }

    if ("editar".equals(action)) {
      int id = Integer.parseInt(req.getParameter("id"));
      req.setAttribute("disciplina", repo.findById(id));
      req.getRequestDispatcher("form-disciplina.jsp")
          .forward(req, resp);
    }

    if ("delete".equals(action)) {
      int id = Integer.parseInt(req.getParameter("id"));
      repo.delete(id);
      resp.sendRedirect("disciplinas");
    }
  }

  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws IOException {

    String nome = req.getParameter("nome");
    String idStr = req.getParameter("id");

    Disciplina d = new Disciplina(
        idStr == null || idStr.isEmpty() ? 0 : Integer.parseInt(idStr),
        nome);

    repo.save(d);

    resp.sendRedirect("disciplinas");
  }
}