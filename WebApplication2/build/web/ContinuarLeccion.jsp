<%-- 
    Document   : ContinuarLeccion
    Created on : 17/06/2022, 02:26:59 AM
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
        <title>Leccion</title>
    </head>
    <%Class.forName("org.gjt.mm.mysql.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/airadb", "root", "password");
        String user = request.getParameter("usuario");
        String nivels = request.getParameter("nivels");
        String leccion = request.getParameter("leccion");
    %>
    <body>

        <div class="nav-bg">
            <nav class="navegacion-principal contenedor">
                <a href="index.html"><img src="logo1-removebg-preview.png" alt="Logo" class="element_nav"></a>
                <form action="home.jsp" method="post">                    
                    <input type="hidden" value="<%=user%>" name="usuario">
                    <input type="hidden" value="<%=nivels%>" name="nivels" >
                    <input type="hidden" value="<%=leccion%>" name="leccion" >
                    <input type="submit" class="btn_nav" value="Inicio">
                </form>
                
            </nav>
        </div>
        <%

            //Obtener la leccion actual
            Statement set = con.createStatement();
            ResultSet res = set.executeQuery("select * from " + user + "");
            while (res.next()) {
                int leccion2 = res.getInt("leccion");
                int numero_pregunta = res.getInt("numero_pregunta");
                String completado = res.getString("completado");
                if (completado.equals("si")) {
                    Statement set2 = con.createStatement();
                    set2.executeUpdate("update usuarios set leccion = " + leccion2 + " where usuario = '" + user + "' ");
                    continue;
                } else {
                    String pregunta = res.getString("pregunta");
        %>
        <main class="formulario__grupo ">
            <form action="Revisar">
                <label class="formulario__label"><%=pregunta%></label>
                <input type="hidden" value="<%=user%>" name="usuario">

                <input class="formulario__input" name="<%=numero_pregunta%>" type="text">
                <input type="submit" class="formulario__btn" value="Revisar">
            </form>
        </main>
        <%break;
                }
            }%>
    </div>
</body>
</html>
