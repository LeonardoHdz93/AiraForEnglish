<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

            String user, nivels;
            user = request.getParameter("usuario");
            Statement stf = con.createStatement();
            ResultSet rsf;
            rsf = stf.executeQuery("select * from usuarios where usuario = '" + user + "'");
            while (rsf.next()) {
                int nivel = rsf.getInt("nivel");
                Statement sst = con.createStatement();
                ResultSet rrs = sst.executeQuery("select nivel_nombre from niveles where id = " + nivel + " ");
                rrs.next();
                nivels = rrs.getString("nivel_nombre");
                Statement user_table = con.createStatement();
                            ResultSet user_tableres = user_table.executeQuery("select leccion from usuarios where usuario = '" + user + "'");
                            user_tableres.next();
                            int leccion = user_tableres.getInt("leccion");

        %>
        
        <div class="nav-bg">
            <nav class="navegacion-principal contenedor">
                <a href="index.html"><img src="logo1-removebg-preview.png" alt="Logo" class="element_nav"></a>
                <form action="Desafios.jsp" method="post">
                    
                        <input type="hidden" value="<%=user%>" name="usuario" >
                        <input type="hidden" value="<%=nivels%>" name="nivels" >
                        <input type="hidden" value="<%=leccion%>" name="leccion" >
                        <input type="submit" class="btn_nav" value="Desafios">
                    </form>
                
            </nav>
        </div>
        <main class="main-curso">
            <div class="curso1 curso">
                <div class="curso1-info">
                    <div class="curso1-info-lecciones">
                        <h2>Lecciones Disponibles</h2>
                        <%int verificar = 1;
                            Statement leccionesst = con.createStatement();
                            ResultSet leccionesres = leccionesst.executeQuery("select * from " + user + "");
                            while (leccionesres.next()) {
                                int curso1_info_lecciones = leccionesres.getInt("leccion");
                                if (verificar == curso1_info_lecciones) {
                                    out.println("                  <p>Leccion " + curso1_info_lecciones + "</p>\n");
                                    verificar++;
                            } else {
                                continue;
                            }
                        }%>

                    </div>
                </div>


            </div>

            <div class="curso2 curso">
                <div class="curso2-info">
                    <h1 class="curso2-info-bienvenida">Bienvenido <%=user%></h1>
                    <h2 class="curso2-info-nivel" id="Leo123">Nivel actual <%=nivels%></h2>
                    <h3 class="curso2-info-leccion">Leccion actual <%=leccion%></h3>
                </div>

                <form action="ContinuarLeccion.jsp" method="post" class="curso1-botones">
                    <input type="hidden" value="<%=user%>" name="usuario">
                    <input type="hidden" value="<%=nivels%>" name="nivels" >
                        <input type="hidden" value="<%=leccion%>" name="leccion" >
                    <input type="submit" value="Continuar Leccion" class="boton">
                </form>
            </div>
            <div class="curso3 curso">
                <svg xmlns="http://www.w3.org/2000/svg" class="curso3-svg icon icon-tabler icon-tabler-world" width="150" height="150"
                     viewBox="0 0 24 24" stroke-width="1.5" stroke="#000000" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                <circle cx="12" cy="12" r="9" />
                <line x1="3.6" y1="9" x2="20.4" y2="9" />
                <line x1="3.6" y1="15" x2="20.4" y2="15" />
                <path d="M11.5 3a17 17 0 0 0 0 18" />
                <path d="M12.5 3a17 17 0 0 1 0 18" />
                </svg>

                
            </div>
        </main>
                   
    </body>
<%}%>
</html>
