<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    background: #f4f6f9;
}

.navbar {
    background: #1f2937;
    padding: 15px 30px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

.nav-title {
    color: #fff;
    font-size: 18px;
    font-weight: bold;
}

.nav-links a {
    color: #d1d5db;
    text-decoration: none;
    margin-left: 20px;
    font-weight: 500;
    transition: 0.2s;
}

.nav-links a:hover {
    color: #ffffff;
}

.nav-links a.active {
    color: #3b82f6;
    font-weight: bold;
}
</style>

<div class="navbar">
    <div class="nav-title">Sistema Escolar</div>

    <div class="nav-links">
        <a href="alunos">Alunos</a>
        <a href="professores">Professores</a>
        <a href="disciplinas">Disciplinas</a>
        <a href="turmas">Turmas</a>
    </div>
</div>