/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

/**
 *
 * @author erome
 */
public class Banca {
    private int dinero;

    public Banca(int dinero) {
        this.dinero = dinero;
    }
    
    public int getDinero(){
        return this.dinero;
    }
    
    public int movimiento(int cantidad){
        if(cantidad > dinero){
            int aux = dinero;
            dinero = 0;
            return aux;
        }
        
        dinero = dinero - cantidad;
        return cantidad;
    }
}
