/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.maestredam.psp;

/**
 *
 * @author Eugenio Romero ciudad
 */
public class Cuenta {
    private float saldo;
    
    public Cuenta(float saldo){
        this.saldo = saldo;
    }

    public synchronized float getSaldo() {
        return saldo;
    }
    
    public synchronized void actualizarSaldo(float cantidad){
        this.saldo = this.saldo+cantidad;
    }
    
}
