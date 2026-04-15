<%@ page import="com.escola.model.Professor" %>

<jsp:include page="header.jsp"/>

<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f5f6fa;
    margin: 0;
  }
  
  .container {
    width: 90%;
    max-width: 600px;
    margin: 40px auto;
  }
  
  h2 {
    color: #2f3640;
    margin-bottom: 20px;
  }
  
  .card {
    background: white;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    color: #555;
    font-size: 14px;
  }
  
  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
  }
  
  input:focus {
    border-color: #00a8ff;
    outline: none;
  }
  
  .actions {
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
  }
  
  .btn {
    text-decoration: none;
    padding: 8px 14px;
    border-radius: 6px;
    font-size: 14px;
    border: none;
    cursor: pointer;
    transition: 0.2s;
  }
  
  .btn-save {
    background-color: #4cd137;
    color: white;
  }
  
  .btn-save:hover {
    background-color: #44bd32;
  }
  
  .btn-back {
    background-color: #7f8c8d;
    color: white;
  }
  
  .btn-back:hover {
    background-color: #636e72;
  }
</style>

<%
  Professor p = (Professor) request.getAttribute("professor");
%>

<div class="container">

  <h2><%= (p != null ? "Editar Professor" : "Novo Professor") %></h2>

  <div class="card">

    <form method="post" action="professores">

      <input type="hidden" name="id"
      value="<%= (p != null ? p.getId() : "") %>">

      <div class="form-group">
        <label>Nome do Professor</label>
        <input name="nome"
        placeholder="Digite o nome do professor"
        required
        value="<%= (p != null ? p.getNome() : "") %>">
      </div>

      <div class="actions">
        <a href="professores" class="btn btn-back">Voltar</a>
        <button type="submit" class="btn btn-save">Salvar</button>
      </div>

    </form>

  </div>

</div>