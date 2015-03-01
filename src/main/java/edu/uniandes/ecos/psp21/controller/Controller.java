package edu.uniandes.ecos.psp21.controller;

import edu.uniandes.ecos.psp21.model.Calculate;

/**
 * Clase controladora de la aplicación
 *
 * @version 1
 * @author Diego
 */
public class Controller {

    /**
     * Metodo que hace el llamado a los procesos que hacen los calculos del
     * negocio
     *
     * @return 
     * @throws Exception
     */
    public String ejecutar() throws Exception {
        try {
            StringBuilder st = new StringBuilder();
/*
            Calculate c = new Calculate();
            c.calcularX(6D, 0.20,0.00001);
            st.append(calcularRespuesta(c, "Test 1"));

            Calculate c2 = new Calculate();
            c2.calcularX(15D, 0.45,0.00001);
            st.append(calcularRespuesta(c2, "Test 2"));
*/
            Calculate c3 = new Calculate();
            c3.calcularX(4D, 0.495,0.00001);
            st.append(calcularRespuesta(c3, "Test 3"));

            return st.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Calcula la respuesta del servlet de acuerdo al resultado de los procesos
     *
     * @param c
     * @param titulo
     * @return st
     */
    public String calcularRespuesta(Calculate c, String titulo) {
        StringBuilder st = new StringBuilder();

        st.append("<h2>").append(titulo).append("</h1>");
        st.append("<dl>");
        st.append("<dt><b>Datos ingresados:</b></dt>");
        st.append("<dd><b>dof: </b>").append(c.getDof()).append("</dd>");
        st.append("<dd><b>p esperado:</b>").append(c.getP()).append("</dd>");
        st.append("<dd><b>e:</b>").append(c.getE()).append("</dd>");
        st.append("<dt></dt>");
        st.append("<dt><b>Datos calculados:</b></dt>");
        st.append("<dd><b>p Calculado: </b>").append(c.getPCalculado()).append("</dd>");
        st.append("<dd><b>x: </b>").append(c.getX()).append("</dd>");
        st.append("</dl>");
        st.append("</br>");
        return st.toString();
    }

}
