
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

public class Registro extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nom, ap, user, email, pass, pass2, ip, ipr;;
            int puerto, puertor;

            nom = request.getParameter("nombre");
            ap = request.getParameter("apellido");
            user = request.getParameter("usuario");
            email = request.getParameter("correo");
            pass = request.getParameter("password");

            ip = request.getLocalAddr();
            puerto = request.getLocalPort();

            ipr = request.getRemoteAddr();
            puertor = request.getRemotePort();
            try {
                String q = "insert into usuarios (nombre,apellido,usuario,correo,contrasena) values ('" + nom + "','" + ap + "','" + user + "','" + email + "','" + pass + "')";
                set.executeUpdate(q);

                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>"
                        + "<html lang=\"en\">\n"
                        + "\n"
                        + "    <head>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <link rel=\"stylesheet\" href=\"css/normalize.css\">\n"
                        + "  <link rel=\"stylesheet\" href=\"css/navegacion.css\">\n"
                        + "  <link rel=\"stylesheet\" href=\"style.css\">"
                        + "        <link rel=\"stylesheet\" href=\"css/Sesion.css\">\n"
                        + "        <title>Iniciar Sesion</title>\n"
                        + "    </head>\n"
                        + "\n"
                        + "    <body>\n"
                        + "        <div class=\"nav-bg\">\n"
                        + "            <nav class=\"navegacion-principal contenedor\">\n"
                        + "                <a href=\"../html/index.html\"><img src=\"../img/logo (1).png\" alt=\"Logo\" class=\"element_nav\"></a>\n"
                        + "            </nav>\n"
                        + "        </div>\n"
                        + "        <main>\n"
                        + "            <h3>Inicia sesion para ingresar al contenido</h3>\n"
                        + "            <form action=\"InicioSesion\" class=\"formulario\" id=\"formulario\">\n"
                        + "                <!--Grupo Usuario-->\n"
                        + "                <div class=\"formulario__grupo \" id=\"grupo__usuario\">\n"
                        + "                    <label for=\"usuario\" class=\"formulario__label\"> Usuario </label>\n"
                        + "\n"
                        + "                    <div class=\"formulario__grupo-input\">\n"
                        + "                        <input type=\"text\" class=\"formulario__input\" name=\"usuario\" id=\"usuario\" placeholder=\"Usuario123\">\n"
                        + "                        <i class=\"formulario__validacion-estado fas fa-times-circle\" ></i>\n"
                        + "                    </div>\n"
                        + "                    <p class=\"formulario__input-error\"> El usuario debe contener entre 4 y 16 digitos y solo puedes incluir letras, numeros y guion bajo. </p>\n"
                        + "                </div>\n"
                        + "\n"
                        + "                <!--Grupo Contraseña-->\n"
                        + "                <div class=\"formulario__grupo\" id=\"grupo__password\">\n"
                        + "                    <label for=\"password\" class=\"formulario__label\"> Contraseña </label>\n"
                        + "                    <div class=\"formulario__grupo-input\">\n"
                        + "                        <input type=\"password\" class=\"formulario__input\" name=\"password\" id=\"password\">\n"
                        + "                        <i class=\"formulario__validacion-estado fas fa-times-circle\" ></i>\n"
                        + "                    </div>\n"
                        + "                    <p class=\"formulario__input-error\"> La contraseña debe contener entre 8 y 16 digitos. </p>\n"
                        + "                </div>\n"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "                <div class=\"formulario__mensaje\" id=\"formulario__mensaje\">\n"
                        + "                    <p><i class=\"fas fa-exclamation-triangle\"></i> <b> Error </b> Por favor rellena el formulario correctamente </p>\n"
                        + "\n"
                        + "                </div>\n"
                        + "\n"
                        + "                <div class=\" formulario__grupo formulario__grupo-btn-enviar\">\n"
                        + "                    <button type=\"submit\" class=\"formulario__btn\"> Iniciar Sesion </button>\n"
                        + "                    <p class=\"formulario__mensaje-exito\" id=\"formulario__mensaje-exito\"> Formulario enviado con éxito </p>\n"
                        + "                </div>\n"
                        + "                <div class=\"formulario__grupo formulario__grupo-btn-a\">\n"
                        + "                    <p>Si no tienes una cuenta <a href=\"../html/registro.html\">registrate.</a></p>\n"
                        + "                </div>\n"
                        + "                <div class=\"formulario__grupo formulario__grupo-btn-a\">\n"
                        + "                    <p>Inicia sesion como administrador <a href=\"../html/administrador_sesion.html\">aqui.</a></p>\n"
                        + "                </div>\n"
                        + "            </form>\n"
                        + "        </main>\n"
                        + "\n"
                        + "        <script src=\"../js/registro.js\"></script>\n"
                        + "\n"
                        + "    </body>\n"
                        + "\n"
                        + "</html>");

            } catch (Exception e) {
                System.out.println("Error al registrar");
                System.out.println(e.getMessage());
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error Registrar</title>");
                out.println("</head>");
                out.println("<body>"
                        + "<br>"
                        + "Intentalo de nuevo");
                out.println("<h1>Registro No Exitoso"
                        + "<a href='index.html'>Regresar al menu principal</a></h1>");
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

}
