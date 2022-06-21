<%-- 
    Document   : Desafios
    Created on : 17/06/2022, 05:53:16 AM
    Author     : HP EliteDesk 800 G1
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/navegacion.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/Sesion.css">
        <title>Curso</title>
    </head>

    <body>
        <%
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/airadb", "root", "password");
            String user = request.getParameter("usuario");
            String nivels = request.getParameter("nivels");
            String leccion = request.getParameter("leccion");
        %>
        <div class="nav-bg">
            <nav class="navegacion-principal contenedor">
                <a href="index.html"><img src="logo1-removebg-preview.png" alt="Logo" class="element_nav"></a>
                <form action="home.jsp" method="post">

                    <input type="hidden" value="<%=user%>" name="usuario" >
                    <input type="hidden" value="<%=nivels%>" name="nivels" >
                    <input type="hidden" value="<%=leccion%>" name="leccion" >
                    <input type="submit" class="btnnavav" value="Inicio">
                </form>
                
        </div>
        <main class="contenedor">
            <h2 class="h1_desafios">Desafios</h2>
            <%
            Statement set = con.createStatement();
            ResultSet res = set.executeQuery("select * from "+user+"desafios");
            while(res.next()){
                int numero_desafio = res.getInt("numero_desafio");
                
                String completado = res.getString("completado");
                if (completado.equals("si")) {
                    continue;
                } else {
                    String desafio = res.getString("desafio");
                
            
            %>            
            <div class="desafios">
                <div class="numero_desafio">
                    <h2>Desafio <%=numero_desafio%></h2>
                </div>
                <div class="desafio">
                    <label class="formulario__label"><%=desafio%></label>
                </div>
                
                
                <div class="btn_desafio">
                    <form action="RevisarDesafio" method="post">
                        <input type="hidden" value="<%=user%>" name="usuario">
                        <input type="hidden" value="<%=nivels%>" name="nivels">
                        <input type="hidden" value="<%=leccion%>" name="leccion">
                        <input type="hidden" value="<%=numero_desafio%>" name="numero_desafio">
                        <input name="<%=numero_desafio%>"class="formulario__input" type="text">
                        <input type="submit" class="formulario__btn" value="Ir al desafio">
                    </form>
                </div>
                
            </div>
                        <%}}%>
        </main>
    </body>

</html>