/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP EliteDesk 800 G1
 */
public class Revisar extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet res;

    public void init(ServletConfig cfg) throws ServletException {
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String user = request.getParameter("usuario");
            String pass = request.getParameter("password");

            System.out.println("User = " + user + "");
            try {
                Statement set = con.createStatement();
                ResultSet res = set.executeQuery("select * from " + user + "");
                String verificar = "si";
                while (res.next()) {
                    System.out.println("Entro al while");
                    int numero_pregunta = res.getInt("numero_pregunta");

                    String x = String.valueOf(numero_pregunta);
                    String respuesta_formulario = request.getParameter(x);// obtengo la respuesta del formulario
                    try {
                        Statement set2 = con.createStatement();

                        ResultSet res2 = set2.executeQuery("select * from " + user + " where numero_pregunta = " + numero_pregunta + "");
                        res2.next();
                        String respuesta_correcta = res2.getString("respuesta");//obtengo la respuesta que esta bien 
                        System.out.println("respuesta_formulario = " + respuesta_formulario);
                        System.out.println("respuesta_correcta = " + respuesta_correcta);
                        if (respuesta_formulario.equals(respuesta_correcta)) {//verifico si son iguales 
                            //si si hago un update para que aparezca verificado :)
                            try {
                                set.executeUpdate("update " + user + " set completado = '" + verificar + "' where numero_pregunta = " + numero_pregunta + "");
                                out.println("<!DOCTYPE html>\n"
                                        + "<html lang=\"en\">\n"
                                        + "\n"
                                        + "<head>\n"
                                        + "    <meta charset=\"UTF-8\">\n"
                                        + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                                        + "    <link rel=\"stylesheet\" href=\"css/normalize.css\">\n"
                                        + "  <link rel=\"stylesheet\" href=\"css/navegacion.css\">\n"
                                        + "  <link rel=\"stylesheet\" href=\"style.css\">\n"
                                        + "    <title>Leccion</title>\n"
                                        + "</head>\n"
                                        + "\n"
                                        + "<body>\n"
                                        + "    <script src=\"curso.js\"></script>\n"
                                        + "    <div class=\"nav-bg\">\n"
                                        + "        <nav class=\"navegacion-principal contenedor\">\n"
                                        + "            <a href=\"home.jsp\"><img src=\"../Pagina/logo (1).pn\" alt=\"Logo\" class=\"element_nav\"></a>\n"
                                        + "            <a href=\"curso.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                                        + "                    class=\"icon icon-tabler icon-tabler-crown\" width=\"44\" height=\"44\" viewBox=\"0 0 24 24\"\n"
                                        + "                    stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                                        + "                    <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                                        + "                    <path d=\"M12 6l4 6l5 -4l-2 10h-14l-2 -10l5 4z\" />\n"
                                        + "                </svg></a>\n"
                                        + "            <a href=\"logros.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\" class=\"icon icon-tabler icon-tabler-shield\"\n"
                                        + "                    width=\"44\" height=\"44\" viewBox=\"0 0 24 24\" stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\"\n"
                                        + "                    stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                                        + "                    <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                                        + "                    <path d=\"M12 3a12 12 0 0 0 8.5 3a12 12 0 0 1 -8.5 15a12 12 0 0 1 -8.5 -15a12 12 0 0 0 8.5 -3\" />\n"
                                        + "                </svg></a>\n"
                                        + "            <a href=\"progreso.html\" class=\"\" id=\"barra-progreso\">\n"
                                        + "                <div class=\"bar bar-70\"></div>\n"
                                        + "            </a>\n"
                                        + "            <a href=\"cuenta.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                                        + "                    class=\"icon icon-tabler icon-tabler-user-circle\" width=\"44\" height=\"44\" viewBox=\"0 0 24 24\"\n"
                                        + "                    stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                                        + "                    <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                                        + "                    <circle cx=\"12\" cy=\"12\" r=\"9\" />\n"
                                        + "                    <circle cx=\"12\" cy=\"10\" r=\"3\" />\n"
                                        + "                    <path d=\"M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855\" />\n"
                                        + "                </svg></a>\n"
                                        + "        </nav>\n"
                                        + "</div>"
                                        + "        <main>\n"
                                        
                                        + "                <p>Respuesta Correcta</p>\n"
                                        + "<form action='home.jsp'>"
                                        + "                <input type=\"hidden\" value=" + user + " name=\"usuario\">"
                                        + "<input type='submit' value='Regresar'>\n"
                                                + "</form>"
                                        + "<form action=\"ContinuarLeccion.jsp\" class=\"curso1-botones\">\n"
                                        + "                    <input type=\"hidden\" value="+user+" name=\"usuario\">\n"
                                        + "                    <input type=\"submit\" value=\"Continuar Leccion\" class=\"boton\">\n"
                                        + "                </form>"
                                        + "        </main>\n"
                                        + "        <script>\n"
                                        + "            alert(\"holaaaa\");\n"
                                        + "        </script>\n"
                                        + "    </div>\n"
                                        + "    </body>\n"
                                        + "    </html> ");
                            } catch (Exception e) {
                                System.out.println("error en el update");
                                System.out.println(e.getMessage());
                            }

                        }//si no pues le digo que esta mal y lo mando al 
                    } catch (Exception e) {
                        System.out.println("res2 problema");
                        System.out.println(e.getMessage());
                        System.out.println(e.getStackTrace());
                    }
                    System.out.println("Hasta aca agarra sin pedo");
                }

            } catch (Exception e) {
                System.out.println("Hubo un error Revisar");
                System.out.println(e.getMessage());
            }
            //obtener las preguntas del formulario

            //Obtener los datos de la tabla +user+ :)
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Revisar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Revisar.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
