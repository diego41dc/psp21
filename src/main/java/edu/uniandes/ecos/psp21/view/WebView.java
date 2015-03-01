package edu.uniandes.ecos.psp21.view;

import edu.uniandes.ecos.psp21.controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Clase que maneja las respuestas posibles que genera el servlet
 *
 * @version 1
 * @author Diego
 */
public class WebView extends HttpServlet {

    /**
     * Metodo inicial del servlet
     *
     * @param args
     */
    public static void main(String[] args) {
        //Server server = new Server(8380);
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new WebView()), "/*");

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(WebView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que captura los llamados del doGet
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showHome(resp);
        } catch (Exception ex) {
            error(resp, ex);
            Logger.getLogger(WebView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que captura los llamados del doPost
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showHome(resp);
            Controller controller = new Controller();
            show(resp, controller.ejecutar());
        } catch (Exception ex) {
            error(resp, ex);
            Logger.getLogger(WebView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicia el contenido de la pagina del servlet
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private static void showHome(HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder home = new StringBuilder();
        home.append("<html>");
        home.append("<h2>PSP2.1 Program!</h1>");
        home.append("<h2>Este programa integra num&eacute;ricamente una funci&oacute;n usando la regla de Simpson y calculando din&aacute;micamente el valor de x.</h2>");
        home.append("<form action=\"calcular\" method=\"post\"> \n<input type=\"submit\" value=\"Calcular\">\n</form> ");
        home.append("</html>");
        show(resp, home.toString());
    }

    /**
     * Crea la respuesta de error que se genere en el proceso
     *
     * @param req
     * @param resp
     * @param error
     * @throws ServletException
     * @throws IOException
     */
    private static void error(HttpServletResponse resp, Exception e) throws ServletException, IOException {
        show(resp, "Error!!!");
        show(resp, e.getMessage() + ": " + e.getCause());
    }

    /**
     * Crea la respuesta de los resultados de los procesos ejecutados
     *
     * @param req
     * @param resp
     * @param respuesta
     * @throws ServletException
     * @throws IOException
     */
    private static void show(HttpServletResponse resp, String respuesta) throws ServletException, IOException {
        resp.getWriter().println(respuesta);
    }
}
