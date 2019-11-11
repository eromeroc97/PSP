/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestorrecursos;

/**
 *
 * @author Carlos Rojas Rivero
 * Fecha:
 * Clase que...
 * 
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws InterruptedException {
                int MAX_PRODUCTORES     = 5;
                int MAX_CONSUMIDORES    = 7;
                int MAX_ELEMENTOS       = 10;

                Thread[] hilosProductor;
                Thread[] hilosConsumidor;

                hilosProductor   = new Thread[MAX_PRODUCTORES];
                hilosConsumidor  = new Thread[MAX_CONSUMIDORES];

                Cola colaCompartida=new Cola(MAX_ELEMENTOS);

                /*Construimos los productores*/
                for (int i=0; i<MAX_PRODUCTORES; i++){
                        Productor productor=new Productor(colaCompartida);
                        hilosProductor[i]=new Thread(productor);
                        hilosProductor[i].start();
                }
                /*Construimos los consumidores*/
                for (int i=0; i<MAX_CONSUMIDORES; i++){
                        Consumidor consumidor=new Consumidor(colaCompartida);
                        hilosConsumidor[i]=new Thread(consumidor);
                        hilosConsumidor[i].start();
                }

                /* Esperamos a que acaben todos los hilos, primero
                productores y luego consumidores
                */
                for (int i=0; i<MAX_PRODUCTORES; i++){
                        hilosProductor[i].join();
                }
                for (int i=0; i<MAX_CONSUMIDORES; i++){
                        hilosConsumidor[i].join();
                }
        }
}
