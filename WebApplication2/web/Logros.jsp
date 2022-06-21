<%-- 
    Document   : Logros
    Created on : 17/06/2022, 10:03:16 AM
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
                <a href="index.html"><img src="../Pagina/logo (1).pn" alt="Logo" class="element_nav"></a>
                <form action="home.jsp" method="post">

                    <input type="hidden" value="<%=user%>" name="usuario" >
                    <input type="hidden" value="<%=nivels%>" name="nivels" >
                    <input type="hidden" value="<%=leccion%>" name="leccion" >
                    <input type="submit" value="Inicio">
                </form>
                <form action="Desafios.jsp" method="post">
                    
                        <input type="hidden" value="<%=user%>" name="usuario" >
                        <input type="hidden" value="<%=nivels%>" name="nivels" >
                        <input type="hidden" value="<%=leccion%>" name="leccion" >
                        <input type="submit" value="Desafios">
                    </form>
                <a href="progreso.html" class="" id="barra-progreso">
                    <div class="bar bar-70"></div>
                </a>
                <a href="cuenta.html" class=""><svg xmlns="http://www.w3.org/2000/svg"
                                                    class="icon icon-tabler icon-tabler-user-circle" width="44" height="44" viewBox="0 0 24 24"
                                                    stroke-width="1.5" stroke="#ffffff" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                    <circle cx="12" cy="12" r="9" />
                    <circle cx="12" cy="10" r="3" />
                    <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                    </svg></a>
            </nav>
                        
        </div>
       <!--Recorrer todos los logros y ver cual esta marcado como completado y lo muestro-->
       
    </body></html>