/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.util.Random;

/**
 *
 * @author erome
 */
public class Apuesta {
    private int cantidad;
    private int numero;
    private Random rand;

    public Apuesta(int cantidad) {
        this.cantidad = cantidad;
        this.rand = new Random();
        this.numero = rand.nextInt(36)+1;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getNumero() {
        return numero;
    }
    
    
}
