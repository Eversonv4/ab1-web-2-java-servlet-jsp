package com.escola.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escola.repository.TurmaRepository;

@WebServlet("/nota")
public class NotaServlet extends HttpServlet {

  private TurmaRepository repo = new TurmaRepository();

  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws IOException {

    int turmaId = Integer.parseInt(req.getParameter("turmaId"));
    int alunoId = Integer.parseInt(req.getParameter("alunoId"));

    Double n1 = req.getParameter("nota1").isEmpty() ? null : Double.parseDouble(req.getParameter("nota1"));
    Double n2 = req.getParameter("nota2").isEmpty() ? null : Double.parseDouble(req.getParameter("nota2"));

    repo.updateNota(turmaId, alunoId, n1, n2);

    resp.sendRedirect("turmas?action=detalhe&id=" + turmaId);
  }
}
