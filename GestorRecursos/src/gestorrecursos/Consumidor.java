/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorrecursos;

/**
 *
 * @author b14-05m
 */
public class Consumidor implements Runnable{
        Cola colaCompartida;
        public Consumidor(Cola cola){
                this.colaCompartida=cola;
        }
        @Override
        public void run() {
                int num;
                while (true){
                        num=colaCompartida.desencolar();
                        if (num!=-1){
                                System.out.println("Consumidor recuperó el numero:"+num);
                        } /* Fin del if*/
                } /*Fin del bucle infinito*/
        } /* Fin del run()*/
} /*Fin de la clase Consumidor*/
