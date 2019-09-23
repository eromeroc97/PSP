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
public class Actualizador implements Runnable{
    
    private Empleado emp;
    private int horas;

    public Actualizador(Empleado emp, int horas) {
        this.emp = emp;
        this.horas = horas;
    }
    
    
    @Override
    public void run() {
        emp.actualizarHoras(this.horas);
    }

}
