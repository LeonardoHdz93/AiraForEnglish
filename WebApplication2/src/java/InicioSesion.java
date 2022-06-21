/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import static java.util.logging.Logger.global;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InicioSesion extends HttpServlet {

    private static String user;
    private Connection con;
    private Statement set;
    private ResultSet res;

    public void init(ServletConfig cfg) throws ServletException {
        //Conexion conexion = new Conexion();
        //conexion.init();
        String url = "jdbc:mysql://localhost/airadb";
        String userName = "root";
        String password = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Conexion no exitos :(");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nombre, apellido, pass, passc, nivels;
            int veces, nivel;

            user = request.getParameter("usuario");
            pass = request.getParameter("password");

            try {
                Statement orden = con.createStatement();
                ResultSet res;
                res = orden.executeQuery("select contrasena as passc from usuarios where usuario = '" + user + "'");
                res.next();
                passc = res.getString("passc");

                if (pass.equals(passc)) {
                    Statement stf = con.createStatement();
                    ResultSet rsf;
                    rsf = stf.executeQuery("select * from usuarios where usuario = '" + user + "'");
                    while (rsf.next()) {
                        nombre = rsf.getString("nombre");
                        apellido = rsf.getString("apellido");
                        veces = rsf.getInt("veces_inicio");
                        nivel = rsf.getInt("nivel");
                        Statement sst = con.createStatement();
                        ResultSet rrs = sst.executeQuery("select nivel_nombre from niveles where id = " + nivel + " ");
                        rrs.next();
                        nivels = rrs.getString("nivel_nombre");

                        if (veces == 0) {
                            Statement leccionesst = con.createStatement();
                            ResultSet leccionesrs = leccionesst.executeQuery("select * from examen");

                            out.println("<!DOCTYPE html>\n"
                                    + "<html lang=\"en\">\n"
                                    + "    <head>\n"
                                    + "        <meta charset=\"UTF-8\">\n"
                                    + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                                    + "        <link rel=\"stylesheet\" href=\"css/normalize.css\">\n"
                                    + "      <link rel=\"stylesheet\" href=\"css/navegacion.css\">\n"
                                    + "      <link rel=\"stylesheet\" href=\"style.css\">\n"
                                    + "        <title>Examen</title>\n"
                                    + "    </head>\n"
                                    + "    \n"
                                    + "<body>\n"
                                    + "        <div class=\"nav-bg\">\n"
                                    + "            <nav class=\"navegacion-principal contenedor\">\n"
                                    + "                <a href=\"index.html\"><img src=\"../Pagina/logo (1).pn\" alt=\"Logo\" class=\"element_nav\"></a>\n"
                                    + "                <a href=\"curso.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                                    + "                        class=\"icon icon-tabler icon-tabler-crown\" width=\"44\" height=\"44\" viewBox=\"0 0 24 24\"\n"
                                    + "                        stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                                    + "                        <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                                    + "                        <path d=\"M12 6l4 6l5 -4l-2 10h-14l-2 -10l5 4z\" />\n"
                                    + "                    </svg></a>\n"
                                    + "                <a href=\"logros.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\" class=\"icon icon-tabler icon-tabler-shield\"\n"
                                    + "                        width=\"44\" height=\"44\" viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\"\n"
                                    + "                        stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                                    + "                        <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                                    + "                        <path d=\"M12 3a12 12 0 0 0 8.5 3a12 12 0 0 1 -8.5 15a12 12 0 0 1 -8.5 -15a12 12 0 0 0 8.5 -3\" />\n"
                                    + "                    </svg></a>\n"
                                    + "                <a href=\"progreso.html\" class=\"\" id=\"barra-progreso\">\n"
                                    + "                    <div class=\"bar bar-70\"></div>\n"
                                    + "                </a>\n"
                                    + "                <a href=\"cuenta.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                                    + "                        class=\"icon icon-tabler icon-tabler-user-circle\" width=\"44\" height=\"44\" viewBox=\"0 0 24 24\"\n"
                                    + "                        stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                                    + "                        <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                                    + "                        <circle cx=\"12\" cy=\"12\" r=\"9\" />\n"
                                    + "                        <circle cx=\"12\" cy=\"10\" r=\"3\" />\n"
                                    + "                        <path d=\"M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855\" />\n"
                                    + "                    </svg></a>\n"
                                    + "            </nav>\n"
                                    + "            </div>\n" + "<form action=\"Examen\">"
                                    + "<h2>Hola " + user + " responde el siguiente examen para identificar el nivel de ingles que tienes</h2>"
                                    + "<h2>Nivel " + nivel + "</h2>");
                            String x = "9";
                            while (leccionesrs.next()) {
                                String pregunta = leccionesrs.getString("pregunta");
                                int num_pregunta = leccionesrs.getInt("numero_pregunta");

                                out.println("<h2 name=\"pregunta\">" + pregunta + "</h2>\n"
                                        + "<input name=" + num_pregunta + " type=\"text\">");

                                int y = Integer.parseInt(x);
                                y++;
                                x = String.valueOf(y);
                            }
                            out.println("<input type=\"submit\" value=\"enviar Examen\">\n"
                                    + "        </form>\n"
                                    + "    </main>\n"
                                    + "</body>\n"
                                    + "\n"
                                    + "</html>");

                        } else {
                            //Crear metodo para actualizar info de la base de datos en caso de que existan actualizaciones :)
                            Statement leccionesa_datast = con.createStatement();
                            Statement user_datast = con.createStatement();
                            ResultSet leccionesa_datares = leccionesa_datast.executeQuery("select * from leccionesa where nivel = " + nivel + "");
                            ResultSet user_datares = user_datast.executeQuery("select * from " + user + "");
                            try {
                                while (leccionesa_datares.next() && user_datares.next()) {
                                    int nivel_leccionesa = leccionesa_datares.getInt("nivel");
                                    int lecciones = leccionesa_datares.getInt("leccion");
                                    int num_pregunta_lecciones = leccionesa_datares.getInt("numero_pregunta");
                                    String pregunta_lecciones = leccionesa_datares.getString("pregunta");
                                    String respuesta_lecciones = leccionesa_datares.getString("respuesta");

                                    int num_pregunta_user = user_datares.getInt("numero_pregunta");
                                    String pregunta_user = user_datares.getString("pregunta");
                                    String respuesta_user = user_datares.getString("respuesta");
                                    System.out.println("npl" + num_pregunta_lecciones);
                                    System.out.println("npu" + num_pregunta_user);
                                    if (num_pregunta_lecciones == num_pregunta_user) {
                                        if (pregunta_lecciones.equals(pregunta_user)) {
                                            if (respuesta_lecciones.equals(respuesta_user)) {
                                                continue;
                                            } else {
                                                Statement update = con.createStatement();
                                                update.executeUpdate("update " + user + " set respuesta = " + respuesta_lecciones + " where pregunta = " + pregunta_lecciones + "");
                                            }
                                        } else {
                                            Statement update = con.createStatement();
                                            update.executeUpdate("update " + user + " set pregunta = " + pregunta_lecciones + " where numero_pregunta = " + num_pregunta_lecciones + "");
                                        }
                                        continue;
                                    } else {
                                        Statement insertar_datast = con.createStatement();
                                        insertar_datast.executeUpdate("insert into " + user + " (nivel,leccion,numero_pregunta,pregunta,respuesta) values (" + nivel_leccionesa + "," + lecciones + "," + num_pregunta_lecciones + ",'" + pregunta_lecciones + "','" + respuesta_lecciones + "')");
                                        break;
                                    }

                                }
                                while (leccionesa_datares.next()) {
                                    int nivel_leccionesa = leccionesa_datares.getInt("nivel");
                                    int lecciones = leccionesa_datares.getInt("leccion");
                                    int num_pregunta_lecciones = leccionesa_datares.getInt("numero_pregunta");
                                    String pregunta_lecciones = leccionesa_datares.getString("pregunta");
                                    String respuesta_lecciones = leccionesa_datares.getString("respuesta");
                                    Statement insertar_datast = con.createStatement();
                                    insertar_datast.executeUpdate("insert into " + user + " (nivel,leccion,numero_pregunta,pregunta,respuesta) values (" + nivel_leccionesa + "," + lecciones + "," + num_pregunta_lecciones + ",'" + pregunta_lecciones + "','" + respuesta_lecciones + "')");
                                }

                            } catch (Exception e) {
                                System.out.println("El pedo esta en el while inicio sesion :)");
                                System.out.println(e.getMessage());
                            }
                            //Obtener los datos de la tabla +user+ :)
                            Statement user_table = con.createStatement();
                            ResultSet user_tableres = user_table.executeQuery("select leccion from usuarios where usuario = '" + user + "'");
                            user_tableres.next();
                            int leccion = user_tableres.getInt("leccion");
                            out.println("<!DOCTYPE html>\n"
                                    + "<html lang=\"en\">\n"
                                    + "\n"
                                    + "<head>\n"
                                    + "    <meta charset=\"UTF-8\">\n"
                                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                                    + "    <link rel=\"stylesheet\" href=\"css/normalize.css\">\n"
                                    + "    <link rel=\"stylesheet\" href=\"css/navegacion.css\">\n"
                                    + "    <link rel=\"stylesheet\" href=\"style.css\">\n"
                                    + "<link rel=\"stylesheet\" href=\"css/Sesion.css\">"
                                    + "    <title>Curso</title>\n"
                                    + "</head>\n"
                                    + "\n"
                                    + "<body>\n"
                                    + "<script></script>"
                                    + "    <div class=\"nav-bg\">\n"
                                    + "        <nav class=\"navegacion-principal contenedor\">\n"
                                    + "            <a href=\"index.html\"><img src=\"logo1-removebg-preview.png\" alt=\"Logo\" class=\"element_nav\"></a>\n"
                                    
                                    + "        </nav>\n"
                                    + "    </div>\n"
                                    + "    <main class=\"contenedor\">\n"
                                    + "<form action=\"home.jsp\" method='post'>\n"
                                    + "        <h2>Eres "+user+" ?</h2>\n"
                                    + "        <input type=\"hidden\"  value="+user+" name=\"usuario\" >\n"
                                    + "        <input type=\"hidden\" value="+nivels+" name=\"nivels\" >\n"
                                    + "        <input type=\"hidden\" value="+leccion+" name=\"leccion\" >\n"
                                    + "        <input type=\"submit\" class='formulario__btn' value=\"Si\" >\n"
                                    + "        <a href=\"index.html\" class=\"boton space-between\">No</a>"
                                    + "    "
                                    + "    </main>\n"
                                            + "<script src='js/debug.js'></script>"
                                    + "</body>\n"
                                    + "\n"
                                    + "</html>"
                            );
                        }
                    }
                }

                /* TODO output your page here. You may use following sample code. */
            } catch (Exception e) {
                System.out.println("Error buscar usuario");
                System.out.println("" + user + "");
                System.out.println(e.getMessage());

                out.println("<h1> No se encontro el usuario, verifica que los datos sean correctos e intentalo de nuevo</h1>"
                        + "<p>Si no tienes una cuenta registrate <a href='Registro.jsp'>aqui</a></p>"
                        + "<a href='index.html'>Regresar al menu principal</a>");
                out.println("</body>");
                out.println("</html>");
            }

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    static String Test() {
        String usert = InicioSesion.user;//To change body of generated methods, choose Tools | Templates.
        return usert;
    }

}
