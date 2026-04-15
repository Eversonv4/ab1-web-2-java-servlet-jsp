<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.escola.model.Disciplina" %>

<jsp:include page="header.jsp"/>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f5f6fa;
    margin: 0;
}

.container {
    width: 90%;
    max-width: 900px;
    margin: 40px auto;
}

h2 {
    color: #2f3640;
    margin-bottom: 20px;
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
    background-color: #4cd137;
    color: white;
}

.btn-primary:hover {
    background-color: #44bd32;
}

.btn-edit {
    background-color: #00a8ff;
    color: white;
}

.btn-edit:hover {
    background-color: #0097e6;
}

.btn-delete {
    background-color: #e84118;
    color: white;
}

.btn-delete:hover {
    background-color: #c23616;
}

.card {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
}

table {
    width: 100%;
    border-collapse: collapse;
}

th {
    text-align: left;
    background-color: #f1f2f6;
    padding: 12px;
}

td {
    padding: 12px;
    border-top: 1px solid #eee;
}

tr:hover {
    background-color: #f9f9f9;
}

.actions a {
    margin-right: 8px;
}
</style>

<div class="container">

    <div class="top-bar">
        <h2>Disciplinas</h2>
        <a class="btn btn-primary" href="disciplinas?action=novo">+ Nova Disciplina</a>
    </div>

    <div class="card">
        <table>
            <tr>
                <th style="width: 80px;">ID</th>
                <th>Nome</th>
                <th style="width: 180px;">Ações</th>
            </tr>

            <%
            List<Disciplina> lista = (List<Disciplina>) request.getAttribute("lista");

            if(lista != null && !lista.isEmpty()){
                for(Disciplina d : lista){
            %>

            <tr>
                <td><%= d.getId() %></td>
                <td><%= d.getNome() %></td>

                <td class="actions">
                    <a class="btn btn-edit"
                       href="disciplinas?action=editar&id=<%= d.getId() %>">
                       Editar
                    </a>

                    <a class="btn btn-delete"
                       href="disciplinas?action=delete&id=<%= d.getId() %>"
                       onclick="return confirm('Deseja excluir esta disciplina?')">
                       Excluir
                    </a>
                </td>
            </tr>

            <%
                }
            } else {
            %>

            <tr>
                <td colspan="3" style="text-align:center; color:#888;">
                    Nenhuma disciplina cadastrada
                </td>
            </tr>

            <%
            }
            %>

        </table>
    </div>

</div>