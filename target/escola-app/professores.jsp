<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.escola.model.Professor" %>

<jsp:include page="header.jsp"/>

<style>
.container {
    width: 90%;
    max-width: 900px;
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
    text-decoration: none;
    padding: 8px 14px;
    border-radius: 6px;
    font-size: 14px;
    transition: 0.2s;
}

.btn-primary {
    background-color: #00a8ff;
    color: #fff;
}

.btn-primary:hover {
    background-color: #00a8ff;
}

.btn-edit {
    background-color: #00a8ff;
    color: white;
    margin-right: 10px;
}

.btn-delete {
    background-color: #e84118;
    color: white;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th {
    background: #f1f5f9;
    text-align: left;
    padding: 10px;
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
List<Professor> professores = (List<Professor>) request.getAttribute("lista");
%>

<div class="container">

<div class="top-bar">
    <h1>Professores</h1>
    <a class="btn btn-primary" href="professores?action=novo">+ Novo Professor</a>
</div>

<table>
<tr>
  <th>ID</th>
  <th>Nome</th>
  <th style="width:150px;">Ações</th>
</tr>

<% for(Professor p : professores){ %>
<tr>
  <td><%= p.getId() %></td>
  <td><%= p.getNome() %></td>
  <td>
    <a class="btn btn-edit" href="professores?action=editar&id=<%= p.getId() %>">Editar</a>
    <a class="btn btn-delete" href="professores?action=delete&id=<%= p.getId() %>">Excluir</a>
  </td>
</tr>
<% } %>

</table>

</div>