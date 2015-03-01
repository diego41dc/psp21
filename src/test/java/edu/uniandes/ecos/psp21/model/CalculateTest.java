package edu.uniandes.ecos.psp21.model;

import junit.framework.TestCase;

/**
 *
 * @author Diego
 */
public class CalculateTest extends TestCase {
    
    public CalculateTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of calcularX1OK method, of class Calculate.
     */
    public void testCalcularX1OK() {
        System.out.println("calcularX1OK");
        Calculate instance = new Calculate();
        instance.calcularX(6D, 0.20, 0.00001, 1D);
        assertEquals("Fallo de calculo de variable pCalculada",0.20001, instance.getPCalculado());
        assertEquals("Fallo de calculo de variable x",0.55340576171875, instance.getX());
    }
    
    /**
     * Test of calcularX2OK method, of class Calculate.
     */
    public void testCalcularX2OK() {
        System.out.println("calcularX2OK");
        Calculate instance = new Calculate();
        instance.calcularX(15D, 0.45, 0.00001, 1D);
        assertEquals("Fallo de calculo de variable pCalculada",0.45, instance.getPCalculado());
        assertEquals("Fallo de calculo de variable x",1.7530517578125, instance.getX());
    }
    
    /**
     * Test of calcularX3OK method, of class Calculate.
     */
    public void testCalcularX3OK() {
        System.out.println("calcularX3OK");
        Calculate instance = new Calculate();
        instance.calcularX(4D, 0.495, 0.00001, 8D);
        assertEquals("Fallo de calculo de variable pCalculada",0.495, instance.getPCalculado());
        assertEquals("Fallo de calculo de variable x",4.603515625, instance.getX());
    }

    
}
