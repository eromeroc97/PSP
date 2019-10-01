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
public class Empleado {
    private int horas;

    public Empleado(int horas) {
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }
    
    public void actualizarHoras(int horas){
        
        synchronized(this){
            this.horas = this.horas + horas;
        }
        
    }
}
