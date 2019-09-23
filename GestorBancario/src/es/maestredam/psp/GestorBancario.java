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
public class GestorBancario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        final int MAX_INGRESADORES = 1000;
        Operacion[] ingresadores = new Operacion[MAX_INGRESADORES];
        Thread[] hilosIngresadores = new Thread[MAX_INGRESADORES];
        
        final int MAX_EXTRACTORES = 1000;
        Operacion[] extractores = new Operacion[MAX_EXTRACTORES];
        Thread[] hilosExtractores = new Thread[MAX_EXTRACTORES];
        
        Cuenta cuenta = new Cuenta(1000);
        
        for(int i = 0; i < MAX_INGRESADORES; i++){
            ingresadores[i]=new Operacion(cuenta, 20);
            hilosIngresadores[i] = new Thread(ingresadores[i]);
            hilosIngresadores[i].start();
        }
        
        for(int i = 0; i < MAX_EXTRACTORES; i++){
            extractores[i]=new Operacion(cuenta, -20);
            hilosExtractores[i] = new Thread(extractores[i]);
            hilosExtractores[i].start();
        }
        
        for(int i = 0; i < MAX_INGRESADORES; i++){
            hilosIngresadores[i].join();
        }
        for(int i = 0; i < MAX_EXTRACTORES; i++){
            hilosExtractores[i].join();
        }
        
        System.out.println("Cuenta: "+cuenta.getSaldo());
    }

}
