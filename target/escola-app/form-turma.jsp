<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.escola.model.*" %>

<jsp:include page="header.jsp"/>

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        background: #f4f6f9;
    }
    
    .container {
        max-width: 800px;
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
    
    .form-group {
        margin-bottom: 18px;
    }
    
    label {
        display: block;
        font-weight: bold;
        margin-bottom: 6px;
        color: #555;
    }
    
    input[type="text"], select {
        width: 100%;
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #ccc;
        font-size: 14px;
    }
    
    .alunos-box {
        border: 1px solid #ddd;
        border-radius: 6px;
        padding: 10px;
        max-height: 200px;
        overflow-y: auto;
        background: #fafafa;
    }
    
    .aluno-item {
        display: flex;
        align-items: center;
        margin-bottom: 6px;
    }
    
    .aluno-item input {
        margin-right: 8px;
    }
    
    button {
        background: #007bff;
        color: white;
        border: none;
        padding: 10px 18px;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
    }
    
    button:hover {
        background: #0056b3;
    }
</style>

<%
    List<Professor> professores = (List<Professor>) request.getAttribute("professores");
    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
    Turma turma = (Turma) request.getAttribute("turma");
    
    Set<Integer> alunosSelecionados = new HashSet<>();
    
    if(turma != null && turma.getAlunos() != null){
        for(Aluno a : turma.getAlunos()){
            alunosSelecionados.add(a.getId());
        }
    }
%>

<div class="container">

    <h1><%= (turma != null ? "Editar" : "Nova") %> Turma</h1>

    <form method="post" action="turmas">

        <input type="hidden" name="id" value="<%= (turma != null ? turma.getId() : "") %>">

        <div class="form-group">
            <label>Nome da Turma</label>
            <input type="text" name="nome"
            value="<%= (turma != null ? turma.getNome() : "") %>" required>
        </div>

        <div class="form-group">
            <label>Professor</label>
            <select name="professor_id" required>
                <% for(Professor p : professores){ %>
                <option value="<%= p.getId() %>"
                    <%= (turma != null && turma.getProfessorId().equals(p.getId())) ? "selected" : "" %>>
                    <%= p.getNome() %>
                </option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label>Alunos</label>
            <div class="alunos-box">
                <% for(Aluno a : alunos){ %>
                <div class="aluno-item">
                    <input type="checkbox" name="alunos" value="<%= a.getId() %>"
                    <%= alunosSelecionados.contains(a.getId()) ? "checked" : "" %>>
                    <span><%= a.getNome() %></span>
                </div>
                <% } %>
            </div>
        </div>

        <button type="submit">Salvar</button>

    </form>

</div>