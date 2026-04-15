<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.escola.model.Turma" %>

<jsp:include page="header.jsp"/>

<style>
  .container {
    max-width: 1000px;
    margin: 40px auto;
    background: #fff;
    padding: 25px 30px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  }
  
  h1 {
    margin-bottom: 20px;
    color: #333;
  }
  
  .top-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .btn {
    padding: 8px 14px;
    border-radius: 6px;
    text-decoration: none;
    font-weight: bold;
    font-size: 14px;
  }
  
  .btn-primary {
    background: #007bff;
    color: #fff;
  }
  
  .btn-primary:hover {
    background: #0056b3;
  }
  
  .link-detalhe {
    background-color: #2563eb;
    color: white;
    margin-right: 10px;
  }
  
  .link-edit {
    background-color: #16a34a;
    color: white;
    margin-right: 10px;
  }
  
  .link-delete {
    background-color: #dc2626;
    color: white;
  }
  
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th {
    background: #f1f5f9;
    padding: 12px;
    text-align: left;
  }
  
  td {
    padding: 10px;
    border-top: 1px solid #e5e7eb;
  }
  
  tr:hover {
    background: #f9fafb;
  }
</style>

<%
  List<Turma> turmas = (List<Turma>) request.getAttribute("lista");
%>

<div class="container">

  <div class="top-bar">
    <h1>Turmas</h1>
    <a class="btn btn-primary" href="turmas?action=novo">+ Nova Turma</a>
  </div>

  <table>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th style="width:255px;">Ações</th>
    </tr>

    <% for(Turma t : turmas){ %>
    <tr>
      <td><%= t.getId() %></td>
      <td><%= t.getNome() %></td>
      <td>
        <a class="btn link-detalhe" href="turmas?action=detalhe&id=<%= t.getId() %>">Detalhes</a>
        <a class="btn link-edit" href="turmas?action=editar&id=<%= t.getId() %>">Editar</a>
        <a class="btn link-delete" href="turmas?action=delete&id=<%= t.getId() %>">Excluir</a>
      </td>
    </tr>
    <% } %>

  </table>

</div>