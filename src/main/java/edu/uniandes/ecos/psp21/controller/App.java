package edu.uniandes.ecos.psp21.controller;



import edu.uniandes.ecos.psp21.model.Calculate;
import edu.uniandes.ecos.psp21.view.MainView;
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
 * Clase controladora de la aplicación que maneja el Servlet
 * 
 * @version 1
 * @author Diego
 */
public class App extends HttpServlet{

    /**
     *  Metodo inicial del servlet
     * @param args
     */
    public static void main(String[] args) {
        
        //Server server = new Server(8380);
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()), "/*");
        
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *  Metodo que captura los llamados del doGet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        MainView.showHome(req,resp);
    }

    /**
     *  Metodo que captura los llamados del doPost
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                
        try {
            MainView.showHome(req,resp);
            ejecutar(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  Metodo que hace el llamado a los procesos que hacen los calculos del negocio
     * @param req
     * @param resp
     * @throws Exception
     */
    public void ejecutar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        StringBuilder st = new StringBuilder();
        
        Calculate c = new Calculate();
        c.calcularValores(9D,1.1);
        calcularRespuesta(c, st,"Test 1");
        
        Calculate c2 = new Calculate();
        c2.calcularValores(10D,1.1812);
        calcularRespuesta(c2, st,"Test 2");
        
        Calculate c3 = new Calculate();
        c3.calcularValores(30D,2.750);
        calcularRespuesta(c3, st,"Test 3");
        
        MainView.showResults(req, resp, st.toString());
       
    }
    
    /**
     *  Calcula la respuesta del servlet de acuerdo al resultado de los procesos
     * @param c
     * @param st
     * @param titulo
     */
    public void calcularRespuesta(Calculate c, StringBuilder st, String titulo){
        st.append("<h2>"+titulo+"</h1>");
        st.append("<dl>");
        st.append("<dt><b>Datos ingresados:</b></dt>");
        st.append("<dd><b>dof: </b>"+c.getDof()+ "</dd>");
        st.append("<dd><b>x:</b> 0-"+c.getX()+ "</dd>");
        
        st.append("<dt></dt>");
        st.append("<dt><b>Datos calculados:</b></dt>");
        st.append("<dd><b>Segmentos: </b>"+c.getSegmentos()+ "</dd>");
        st.append("<dd><b>p: </b>"+c.getTotalCalculado()+ "</dd>");
        
        st.append("</dl>");
        st.append("</br>");
    }

}
