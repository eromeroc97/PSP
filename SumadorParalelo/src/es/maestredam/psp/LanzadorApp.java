/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.maestredam.psp;

/**
 *
 * @author Eugenio Romero Ciudad
 */
public class LanzadorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        final int MINIMO = 1;
        final int MAXIMO = 10000;
        final int NUM_HILOS = 4;
        
        int rango = MAXIMO / NUM_HILOS;
        
        Sumador[] sumadores = new Sumador[NUM_HILOS];
        
        Thread[] hilos = new Thread[NUM_HILOS];
        
        //Lanzando los hilos
        for(int i = 0; i < NUM_HILOS;i++){
            int limSuperior = (i+1)*rango;
            int limInferior = 1+(limSuperior-rango);
            System.out.printf("%d hasta %d %n",limInferior, limSuperior);
            sumadores[i] = new Sumador(limInferior,limSuperior);
            hilos[i] = new Thread(sumadores[i]);
            hilos[i].start();
            
        }
        
        //Metodo de espera
        for(int i = 0; i < NUM_HILOS;i++)
            hilos[i].join();
        
        int res=0;
        for(int i = 0; i < NUM_HILOS; i++){
            res += sumadores[i].getResultado();
        }
        System.out.println("Resultado: "+res);
    }

}
