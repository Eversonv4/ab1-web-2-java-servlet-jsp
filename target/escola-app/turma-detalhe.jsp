<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

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
    
    input {
        width: 80px;
        padding: 6px;
        border-radius: 6px;
        border: 1px solid #ccc;
        text-align: center;
    }
    
    button {
        background: #10b981;
        color: white;
        border: none;
        padding: 6px 12px;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
    }
    
    button:hover {
        background: #059669;
    }
    
    .status-aprovado {
        color: #16a34a;
        font-weight: bold;
    }
    
    .status-reprovado {
        color: #dc2626;
        font-weight: bold;
    }
    
    .status-pendente {
        color: #6b7280;
    }
    
    form {
        margin: 0;
    }
</style>

<div class="container">

    <h1>Detalhe da Turma</h1>

    <table>
        <tr>
            <th>Aluno</th>
            <th>Nota 1</th>
            <th>Nota 2</th>
            <th>Média</th>
            <th>Status</th>
            <th>Ação</th>
        </tr>

        <%
            List<Map<String,Object>> alunos = (List<Map<String,Object>>) request.getAttribute("alunos");
            int turmaId = (int) request.getAttribute("turmaId");
            
            for(Map<String,Object> a : alunos){
                
                Double n1 = (Double) a.get("nota1");
                Double n2 = (Double) a.get("nota2");
                
                String mediaStr = "--";
                String status = "--";
                String statusClass = "status-pendente";
                
                if(n1 != null && n2 != null){
                    double media = (n1 + n2) / 2;
                    mediaStr = String.format("%.2f", media);
                    
                    if(media >= 7){
                        status = "Aprovado";
                        statusClass = "status-aprovado";
                    } else {
                        status = "Reprovado";
                        statusClass = "status-reprovado";
                    }
                }
            %>

            <tr>
                <td><%= a.get("nome") %></td>

                <td>
                    <form method="post" action="turmas?action=nota">
                        <input type="hidden" name="turmaId" value="<%= turmaId %>">
                        <input type="hidden" name="alunoId" value="<%= a.get("id") %>">
                        <input name="nota1" value="<%= n1 != null ? n1 : "" %>">
                    </td>

                    <td>
                        <input name="nota2" value="<%= n2 != null ? n2 : "" %>">
                    </td>

                    <td><%= mediaStr %></td>

                    <td class="<%= statusClass %>">
                        <%= status %>
                    </td>

                    <td>
                        <button type="submit">Salvar</button>
                    </form>
                </td>
            </tr>

            <% } %>

        </table>

    </div>