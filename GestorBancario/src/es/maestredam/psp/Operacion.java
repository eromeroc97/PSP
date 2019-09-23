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
public class Operacion implements Runnable{
    
    Cuenta cuenta;
    float cantidad;

    public Operacion(Cuenta cuenta, float cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }
        
    @Override
    public void run() {
        cuenta.actualizarSaldo(cantidad);
    }

}
