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
public class Metedor implements Runnable{
    RecursoCompartido shared;
    Random generadorAleatorio;

    public Metedor(RecursoCompartido shared) {
        this.shared = shared;
        generadorAleatorio = new Random();
    }

    @Override
    public void run() {
        while(true){
            int num = generadorAleatorio.nextInt(49)+1;
            while(shared.encolar(num) == false)
                esperaAzar(200, 500);
            
            System.out.println("Metedor metió el numero:"+num);
        }
    }

    private void esperaAzar(int min, int max) {
         int msEspera=generadorAleatorio.nextInt(max-min)+min;
        try {
            Thread.sleep(msEspera);
        } catch (InterruptedException ex) {
            System.out.println("No se pudo dormir");
        }
    }
    
    
    
}
