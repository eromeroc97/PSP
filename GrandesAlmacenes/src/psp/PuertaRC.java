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
public class PuertaRC {
    private boolean ocupada;

    public PuertaRC() {
        this.ocupada = false;
    }
    
    public boolean estaOcupada(){
        return this.ocupada;
    }
    
    public synchronized void liberarPuerta(){
        this.ocupada = false;
    }
    
    public synchronized boolean intentarEntrar(){
        if(this.ocupada) return false;
        
        this.ocupada = true;
        return true;
    }
    
    
}
