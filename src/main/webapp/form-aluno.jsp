<%@ page import="com.escola.model.Aluno" %>

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

.error {
    background-color: #ffe6e6;
    color: #c23616;
    padding: 10px;
    border-radius: 6px;
    margin-bottom: 15px;
}
</style>

<%
Aluno a = (Aluno) request.getAttribute("aluno");
%>

<div class="container">

    <h2><%= (a != null) ? "Editar Aluno" : "Cadastro de Aluno" %></h2>

    <div class="card">

        <% if(request.getAttribute("erro") != null){ %>
            <div class="error">
                <%= request.getAttribute("erro") %>
            </div>
        <% } %>

        <form action="alunos" method="post">

            <input type="hidden" name="id"
                value="<%= a != null ? a.getId() : "" %>">

            <div class="form-group">
                <label>Nome</label>
                <input name="nome"
                       placeholder="Digite o nome do aluno"
                       value="<%= a != null ? a.getNome() : "" %>">
            </div>

            <div class="actions">
                <a href="alunos" class="btn btn-back">Voltar</a>
                <button class="btn btn-save">Salvar</button>
            </div>

        </form>

    </div>

</div>