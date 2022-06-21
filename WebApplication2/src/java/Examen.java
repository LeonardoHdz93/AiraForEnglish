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
public class Examen extends HttpServlet {

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
            String user;
            user = InicioSesion.Test();
            // obtener las respuestas del formulario
            String x = "1";
            float cal_a0 = 0;
            float cal_a1 = 0;
            float cal_a2 = 0;
            float cal_b1 = 0;
            float cal_b2 = 0;
            float cal_c1 = 0;
            float cal_c2 = 0;

            for (int i = 0; i < 8; i++) {
                String respuesta_formulario = request.getParameter(x);
                int y = Integer.parseInt(x);
                int z = y;
                y++;
                x = String.valueOf(y);
                System.out.println("respuesta " + i + " " + respuesta_formulario);//debug
                //obtener las preguntas del formulario
                Statement user_st = con.createStatement();
                Statement nivel_st = con.createStatement();
                ResultSet nivel_res = nivel_st.executeQuery("select * from examen where numero_pregunta = "+z+"");
                while (nivel_res.next()) {
                    String pregunta = nivel_res.getString("pregunta");
                    int nivel = nivel_res.getInt("nivel");
                    int num_pregunta = nivel_res.getInt("numero_pregunta");
                    System.out.println("pregunta " + pregunta);//debug
                    Statement respuesta_st = con.createStatement();
                    ResultSet respuesta_res = respuesta_st.executeQuery("select respuesta from examen where pregunta = '" + pregunta + "'");
                    respuesta_res.next();
                    String respuesta_correcta = respuesta_res.getString("respuesta");
                    System.out.println("Respuesta correcta " + respuesta_correcta);//debug
                    System.out.println("Respuesta_formulario " + respuesta_formulario);//debug
                    if (respuesta_formulario.equals(respuesta_correcta)) {
                        if(num_pregunta <=5 && num_pregunta >0){
                            cal_a0 ++;
                        }
                        if(num_pregunta<=10 && num_pregunta>=6){
                            cal_a1++;
                        }
                        if(num_pregunta<=15 && num_pregunta>=11){
                            cal_a2++;
                        }
                        if(num_pregunta<=20 && num_pregunta>=16){
                            cal_b1++;
                        }
                        if(num_pregunta<=25 && num_pregunta>=21){
                            cal_b2++;
                        }
                        if(num_pregunta<=30 && num_pregunta>=26){
                            cal_c1++;
                        }
                        if(num_pregunta<=35 && num_pregunta>=31){
                            cal_c2++;
                        }
                        
                    } else {
                        System.out.println("Incorrecto");
                    }
                }
            }// hasta aca se repite todo 7 veces 

            // aca debo hacer un metodo para ver en que nivel se a a poner al usuario y modificar ese dato en la base de datos para poder mostrar las lecciones adecuadas 
            out.println("<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <link rel=\"stylesheet\" href=\"../css/normalize.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"../css/navegacion.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"../css/style.css\">\n"
                    + "    <title>Curso</title>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <div class=\"nav-bg\">\n"
                    + "        <nav class=\"navegacion-principal contenedor\">\n"
                    + "            <a href=\"index.html\"><img src=\"../Pagina/logo (1).pn\" alt=\"Logo\" class=\"element_nav\"></a>\n"
                    + "            <a href=\"curso.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                    + "                    class=\"icon icon-tabler icon-tabler-crown\" width=\"44\" height=\"44\" viewBox=\"0 0 24 24\"\n"
                    + "                    stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
                    + "                    <path stroke=\"none\" d=\"M0 0h24v24H0z\" fill=\"none\" />\n"
                    + "                    <path d=\"M12 6l4 6l5 -4l-2 10h-14l-2 -10l5 4z\" />\n"
                    + "                </svg></a>\n"
                    + "            <a href=\"logros.html\" class=\"\"><svg xmlns=\"http://www.w3.org/2000/svg\"\n"
                    + "                    class=\"icon icon-tabler icon-tabler-shield\" width=\"44\" height=\"44\" viewBox=\"0 0 24 24\"\n"
                    + "                    stroke-width=\"1.5\" stroke=\"#ffffff\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\">\n"
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
                    + "    </div>\n"
                    + "    <main class=\"main-curso\">\n"
                    + "        <p>Estamos revisando tu Examen para ponerte en el nivel de ingles que tienes</p>\n"
                    + "        <p>Solo debes iniciar sesion otra vez <a href=\"InicioSesion.jsp\">aqui</a> para acceder a el contenido de la aplicacion</p>\n"
                    + "        \n"
                    + "\n"
                    + "    </main>\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>");
            Statement user_st = con.createStatement();
            ResultSet user_res = user_st.executeQuery("Select * from usuarios where usuario = '" + user + "'");
            user_res.next();
            int veces = user_res.getInt("veces_inicio");
            int nivel = user_res.getInt("nivel");
            veces = 1;
            Statement veces_examenst = con.createStatement();
            veces_examenst.executeUpdate("update usuarios set veces_inicio = '" + veces + "' where usuario = '" + user + "'");
            System.out.println("cal_a0 = "+cal_a0 );
            System.out.println("cal_a0 = "+cal_a1 );
            float cal_a0_f = (cal_a0 * 10) / 5;
            float cal_a1_f = (cal_a1 * 10) / 2;
            float cal_a2_f = (cal_a2 * 10) / 1;
            System.out.println("cal_a0f = "+cal_a0_f );
            System.out.println("cal_a0f = "+cal_a1_f );
            System.out.println("cal_a0f = "+cal_a2_f );
            
            //float cal_b1_f = (cal_b1 * 10) / 5;
            //float cal_b2_f = (cal_b2 * 10) / 5;
            //float cal_c1_f = (cal_c1 * 10) / 5;
            //float cal_c2_f = (cal_c2 * 10) / 5;

            if (cal_a0_f >= 6 && cal_a1_f >= 6) {
                nivel = 2;
            }
            if (cal_a1_f >= 6 && cal_a2_f >= 6) {
                nivel = 3;
            }
            /*if (cal_a2_f >= 6 && cal_b1_f >= 6) {
                nivel = 4;
            }
            if (cal_b1_f >= 6 && cal_b2_f >= 6) {
                nivel = 5;
            }
            if (cal_b2_f >= 6 && cal_c1_f >= 6) {
                nivel = 6;
            }
            if (cal_c1_f >= 6 && cal_c2_f >= 6) {
                nivel = 7;
            }*/
            Statement calst = con.createStatement();
            calst.executeUpdate("update usuarios set nivel = '" + nivel + "' where usuario = '" + user + "'");
            
            
           // aca toca crear la tabla del usuario para poder acceder aa las lecciones y todo eso 
           Statement crear_tabla_usuariost = con.createStatement();
           Statement crear_tabla_usuariodesa = con.createStatement();
           try{
           crear_tabla_usuariost.executeUpdate("create table "+user+"(nivel int(2),leccion int(2) not null, numero_pregunta int(10) not null auto_increment primary key, pregunta char(50) not null,respuesta char(50) not null,completado char(2) not null default 'no' ,foreign key(nivel) references niveles (id))" );
           crear_tabla_usuariost.executeUpdate("create table "+user+"desafios(numero_desafio int(10) not null auto_increment primary key, desafio char(50) not null,desafio_respuesta char(50) not null,completado char(2) not null default 'no' )" );
           }catch(Exception e){
               System.out.println("El pedo esta en el crear la tabla ");
               System.out.println(e.getMessage());
           }
           Statement leccionesa_datast = con.createStatement();
           Statement desafios_data = con.createStatement();
           ResultSet leccionesa_datares = leccionesa_datast.executeQuery("select * from leccionesa where nivel = "+nivel+"");
           ResultSet desafios_datares = desafios_data.executeQuery("select * from desafios ");
           try{
           while (leccionesa_datares.next()){
               int nivel_leccionesa = leccionesa_datares.getInt("nivel");
               int lecciones = leccionesa_datares.getInt("leccion");
               int num_pregunta_lecciones = leccionesa_datares.getInt("numero_pregunta");
               String pregunta_lecciones = leccionesa_datares.getString("pregunta");
               String respuesta_lecciones = leccionesa_datares.getString("respuesta");
               
               Statement insertar_datast = con.createStatement();
               insertar_datast.executeUpdate("insert into "+user+" (nivel,leccion,numero_pregunta,pregunta,respuesta) values ("+nivel_leccionesa+","+lecciones+","+num_pregunta_lecciones+",'"+pregunta_lecciones+"','"+respuesta_lecciones+"')");
               
           }while(desafios_datares.next()){
               String desafio = desafios_datares.getString("desafio");
               String respuesta_desafio = desafios_datares.getString("desafio_respuesta");
               Statement isnertar_data_desafio = con.createStatement();
               isnertar_data_desafio.executeUpdate("insert into "+user+"desafios (desafio,desafio_respuesta) values ('"+desafio+"','"+respuesta_desafio+"')");
           }
           }catch(Exception e){
               System.out.println("El pedo esta en el while :)");
               System.out.println(e.getMessage());
           }
           
        } catch (Exception e) {
            System.out.println("algo anda mal");
            System.out.println(e.getMessage());
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
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
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
