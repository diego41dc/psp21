package edu.uniandes.ecos.psp21.model;

/**
 * Clase que realiza los calculos del negocio
 *
 * @version 1
 * @author Diego
 */
public class Calculate {

    private Double dof;
    private Double p;
    private Double x;
    private Double e;
    private Double pCalculado;

    /**
     * Contructor por defecto
     */
    public Calculate() {
    }

    /**
     * Método que calcula la integral de acuerdo al rango de error
     *
     * @param dof
     * @param p
     * @param e
     */
    public void calcularX(Double dof, Double p, Double e) {

        this.dof = dof;
        this.p = p;
        this.e = e;
        x = 1D;
        Double resultado;
        Double d = 0.5D;
        resultado = calcularIntegral(10, dof, x);
        System.out.println("Cosa1");
        while (Math.abs(resultado - p) > e) {
            System.out.println("Cosa");
            Double resultadoTmp1 = calcularIntegralAproximada(dof, x + d, e);
            Double resultadoTmp2 = calcularIntegralAproximada(dof, x - d, e);
            Double diferenciaTmp1 = resultadoTmp1 - p;
            Double diferenciaTmp2 = resultadoTmp2 - p;

            if (Math.abs(diferenciaTmp1) > Math.abs(diferenciaTmp2)) {
                x = x - d;
                resultado = resultadoTmp2;
            } else {
                x = x + d;
                resultado = resultadoTmp1;
            }
            d = d / 2;
        }
System.out.println("Cosa2");
        this.pCalculado = aproximar(resultado);

    }

    /**
     * Calcula laintegral usando el metodo de Simpson
     *
     * @param dof
     * @param x
     * @param e
     * @return
     */
    private Double calcularIntegralAproximada(Double dof, Double x, Double e) {
        Integer segmentos = 10;
        Double resultado = 0D;
        Double resultadoTmp = calcularIntegral(segmentos, dof, x);

        while (Math.abs(resultado - resultadoTmp) >= e) {
            resultado = resultadoTmp;
            segmentos = segmentos + 10;
            resultadoTmp = calcularIntegral(segmentos, dof, x);
        }
        return aproximar(resultado);
    }

    /**
     * Método que calcula la integral de acuerdo a los parámetros ingresados
     *
     * @param segmentos
     * @param dof
     * @param x
     * @return total - Valor de la integral
     */
    private Double calcularIntegral(Integer segmentos, Double dof, Double x) {

        Double c1 = (dof + 1) / 2;
        Double w = x / segmentos;
        Double xi = 0D;
        Double total = 0D;
        Double r1, r2;

        if (dof % 2 == 0) {
            r1 = factorialDecimal((dof + 1D), 2D);
            r2 = factorialEntero(dof / 2);
        } else {
            r1 = factorialEntero(((dof + 1D) / 2));
            r2 = factorialDecimal(dof, 2D);
        }

        for (int i = 0; i <= segmentos; i++) {
            Double v1 = 1 + (Math.pow(xi, 2) / dof);
            Double v2 = Math.pow(v1, -1 * c1);
            Double v3 = r1 / (Math.pow((dof * Math.PI), 0.5) * r2);
            Double v4 = v2 * v3;

            Double multiplicador;

            if (i == 0 || i == segmentos) {
                multiplicador = 1D;
            } else if (i % 2 != 0) {
                multiplicador = 4D;
            } else {
                multiplicador = 2D;
            }

            Double v5 = (w / 3) * multiplicador * v4;
            xi += w;
            total += v5;
        }
        return total;
    }

    /**
     * Método que aproxima a 5 decimales el valor ingresado
     *
     * @param d
     * @return Double valor de la aproximacion
     */
    private Double aproximar(Double d) {
        return (Math.round(d * 100000D) / 100000D);
    }

    /**
     * Calcula el factorial de un número entero
     *
     * @param numero
     * @return
     */
    private Double factorialEntero(Double numero) {
        Double factorial = 1D;
        numero--;

        while (numero != 0) {
            factorial = factorial * numero;
            numero--;
        }
        return factorial;
    }

    /**
     * Calcula el factorial de un número fraccionario
     *
     * @param numerador
     * @param denominador
     * @return
     */
    private Double factorialDecimal(Double numerador, Double denominador) {
        Double factorial = 1D;
        numerador = numerador - 2D;
        while (numerador >= 1) {
            factorial = factorial * (numerador / denominador);
            numerador = numerador - 2D;
        }
        return factorial * Math.sqrt(Math.PI);
    }

    /**
     * Get del dof del proceso
     *
     * @return dof
     */
    public Double getDof() {
        return dof;
    }

    /**
     * Get del x del proceso
     *
     * @return x
     */
    public Double getX() {
        return x;
    }

    /**
     * Get del pEsperado
     *
     * @return p
     */
    public Double getP() {
        return p;
    }

    /**
     * Get del e
     *
     * @return e
     */
    public Double getE() {
        return e;
    }

    /**
     * Get del pCalculado
     *
     * @return pCalculado
     */
    public Double getPCalculado() {
        return pCalculado;
    }

}
