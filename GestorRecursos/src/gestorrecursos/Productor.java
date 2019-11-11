/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorrecursos;

import java.util.Random;

/**
 *
 * @author b14-05m
 */
public class Productor implements Runnable{
        Cola colaCompartida;
        Random generadorAleatorio;
        public Productor(Cola cola){
                this.colaCompartida=cola;
        }
        public void run() {
           while (true){
                   int num=(int) (Math.random()*10);
                   while (colaCompartida.encolar(num)==false){
                           esperarAzar(3000);
                   } /*Fin del while*/

                   System.out.println("Productor encoló el numero:"+num);
           } /*Fin del while externo*/
        } /*Fin de run()*/
          private void esperarAzar(int msMax){
        int msEl=generadorAleatorio.nextInt(msMax);
        try {
            Thread.sleep(msEl);
        } catch (InterruptedException ex) {
            System.out.println("lol");
        }
    }
} /*Fin de la clase*/