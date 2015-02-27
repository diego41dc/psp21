package edu.uniandes.ecos.psp21.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase que maneja las respuestas posibles que genera el servlet
 * @version 1
 * @author Diego
 */
public class MainView{
    
    /**
     * Inicia la pagina del servlet
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public static void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h2>PSP2.1 Program!</h1>");
        pw.println("<h2>Este programa integra num&eacute;ricamente una funci&oacute;n usando la regla de Simpson.</h2>");
        
        pw.write("<form action=\"calcular\" method=\"post\"> \n" +
        "    <input type=\"submit\" value=\"Calcular\">\n" +
        "</form> ");
        pw.write("</html>");

    }
    
    /**
     *  Crea la respuesta de los resultados de los procesos ejecutados
     * @param req
     * @param resp
     * @param respuesta
     * @throws ServletException
     * @throws IOException
     */
    public static void showResults(HttpServletRequest req, HttpServletResponse resp, String respuesta) throws ServletException, IOException {
        resp.getWriter().println(respuesta);
    }
    
    /**
     *  Crea la respuesta de error que se genere en el proceso
     * @param req
     * @param resp
     * @param error
     * @throws ServletException
     * @throws IOException
     */
    public static void error(HttpServletRequest req, HttpServletResponse resp, String error) throws ServletException, IOException {
        resp.getWriter().println("Error!!!");
        resp.getWriter().println(error);
    }
    
}
