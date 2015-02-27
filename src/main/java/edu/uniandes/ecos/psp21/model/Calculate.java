package edu.uniandes.ecos.psp21.model;

/**
 * Clase que realiza los calculos del negocio
 * 
 * @version 1
 * @author Diego
 */
public class Calculate 
{

    private String error;
    private Double dof;
    private Double x;
    private Double totalCalculado;
    private Integer segmentos;
    
    /**
     * Contructor por defecto
     */
    public Calculate(){}
    
    /**
     *  Método que calcula la integral de acuerdo al rango de error
     * 
     * @param dof
     * @param x
     */
    public void calcularValores(Double dof, Double x){
        try {
            this.dof = dof;
            this.x = x;
            Double e = 0.00001;
            
            segmentos = 10;
            Double resultado = 0D;
            Double resultadoTmp = calcularIntegral(segmentos, dof, x);
            
            while(Math.abs(resultado-resultadoTmp)>=e){
                resultado = resultadoTmp;
                segmentos = segmentos+10;
                resultadoTmp = calcularIntegral(segmentos, dof, x);
            }
            this.totalCalculado = aproximar(resultado);
            
        } catch (Exception e){
            error = e.getMessage();
        }
    }
    
    /**
     * Método que calcula la integral de acuerdo a los parámetros ingresados
     * @param segmentos
     * @param dof
     * @param x
     * @return 
     */
    private Double calcularIntegral(Integer segmentos,Double dof, Double x){
        
        Double c1 = (dof+1)/2;
        Double w = x/segmentos;
        Double xi = 0D;
        Double total = 0D;
        Double r1,r2;

        if(dof%2 == 0){
            r1 = factorialDecimal((dof+1D),2D);
            r2 = factorialEntero(dof/2);
        } else {
            r1 = factorialEntero(((dof+1D)/2));
            r2 = factorialDecimal(dof,2D);
        }

        for(int i = 0; i<=segmentos;i++){
            Double v1 = 1+(Math.pow(xi, 2)/dof);
            Double v2 = Math.pow(v1, -1 * c1);
            Double v3 = r1/(Math.pow((dof * Math.PI), 0.5) * r2);
            Double v4 = v2 * v3;

            Double multiplicador;
            
            if(i == 0 || i == segmentos){
                multiplicador = 1D;
            } else if(i%2 != 0){
                multiplicador = 4D;
            } else {
                multiplicador = 2D;
            }
            
            Double v5 = (w/3) * multiplicador * v4;
            xi+= w;
            total+= v5;
        }
        return total;
    }
    
    /**
     * Método que aproxima a 5 decimales el valor ingresado
     * @param d
     * @return 
     */
    private Double aproximar(Double d){
        return (Math.round(d*100000D)/100000D);
    }
    
    /**
     * Calcula el factorial de un número entero
     * @param numero
     * @return 
     */
    private Double factorialEntero(Double numero){
        Double factorial = 1D; 
        numero--;
         
        while ( numero!=0 ) {
            factorial=factorial*numero;
            numero--;
        }
        return factorial;
    }
    
    /**
     * Calcula el factorial de un número fraccionario
     * @param numerador
     * @param denominador
     * @return 
     */
    private Double factorialDecimal(Double numerador, Double denominador){
        Double factorial = 1D;  
        
        numerador = numerador-2D;
        while(numerador>=1){
            factorial = factorial*(numerador/denominador);
            numerador = numerador-2D;
        } 
        return factorial * Math.sqrt(Math.PI);
    }
    
    /**
     *  Get del error del proceso
     * @return error
     */
    public String getError() {
        return error;
    }

    /**
     *  Get del dof del proceso
     * @return dof
     */
    public Double getDof() {
        return dof;
    }

    /**
     *  Get del total calculado del proceso
     * @return totalCalculado
     */
    public Double getTotalCalculado() {
        return totalCalculado;
    }

    /**
     *  Get del x del proceso
     * @return x
     */
    public Double getX() {
        return x;
    }

    /**
     *  Get de los segmentos del proceso
     * @return segmentos
     */
    public Integer getSegmentos() {
        return segmentos;
    }
}
