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

public class ContinuarLeccion extends HttpServlet {

    private static String user;
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
            InicioSesion sesion = new InicioSesion();
            String user = InicioSesion.Test();
            //Obtener la leccion actual
            Statement set = con.createStatement();
            ResultSet res = set.executeQuery("select * from " + user + "");
            while (res.next()) {
                int leccion = res.getInt("leccion");
                int numero_pregunta = res.getInt("numero_pregunta");
                String completado = res.getString("completado");
                if (completado.equals("si")) {
                    Statement set2 = con.createStatement();
                    set2.executeUpdate("update usuarios set leccion = " + leccion + " where usuario = '" + user + "' ");
                    continue;
                } else {
                    String pregunta = res.getString("pregunta");
                    out.println("<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "<head>\n"
                            + "    <meta charset=\"UTF-8\">\n"
                            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                            + "    <title>Pregunta</title>\n"
                            + "</head>\n"
                            + "<body>\n"
                            + "    <form action=\"Revisar\" method=\"\">\n"
                            + "        <p>"+pregunta+"</p>\n"
                            + "        <input name="+numero_pregunta+" type=\"text\">\n"
                            + "        <input type=\"submit\" value=\"Revisar\">\n"
                            + "    </form>\n"
                            + "</body>\n"
                            + "</html>'");
                    break;
                }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ContinuarLeccion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ContinuarLeccion.class.getName()).log(Level.SEVERE, null, ex);
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
