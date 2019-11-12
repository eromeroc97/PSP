/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package psp;

/**
 *
 * @author Eugenio Romero Ciudad
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        final int NUM_ESCRITORES = 50;
        final int TAMANO_MEMORIA = 5;
        
        MemoriaCompartida memoria = new MemoriaCompartida(TAMANO_MEMORIA);
        
        Escritor[] escritores = new Escritor[NUM_ESCRITORES];
        
        for(int i = 0; i < NUM_ESCRITORES; i++){
            escritores[i] = new Escritor(memoria, "Escritor "+i);
        }
        
        Thread[] hilosEscritores = new Thread[NUM_ESCRITORES];
        
        for(int i = 0; i < NUM_ESCRITORES; i++){
            hilosEscritores[i] = new Thread(escritores[i]);
            hilosEscritores[i].setName(escritores[i].getName());
            hilosEscritores[i].start();
        }
        
        for(int i = 0; i < NUM_ESCRITORES; i++){
            hilosEscritores[i].join();
        }
    }

}
