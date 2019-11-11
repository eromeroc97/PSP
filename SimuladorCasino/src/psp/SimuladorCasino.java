/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

/**
 *
 * @author erome
 */
public class SimuladorCasino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {  
        
        final int APOSTADORES = 4;
        final int APOSTADORES_MARTINGALA = 2;
        
        Banca banca = new Banca(50000);
        Ruleta ruleta = new Ruleta(banca);
        Apostador[] apostadores = new Apostador[APOSTADORES + APOSTADORES_MARTINGALA];
        
        Thread[] hilosApostadores = new Thread[APOSTADORES + APOSTADORES_MARTINGALA];
        Thread hiloRuleta = new Thread(ruleta);
        
        for(int i = 0; i < APOSTADORES; i++){
            apostadores[i] = new Apostador(1000, "Apostador "+(i+1), false, ruleta);
        }
        
        for(int i = APOSTADORES; i < APOSTADORES_MARTINGALA + APOSTADORES; i++){
            apostadores[i] = new Apostador(1000, "Apostador "+(i+1), true, ruleta);
        }
        
        hiloRuleta.start();
        
        for(int i = 0; i < APOSTADORES + APOSTADORES_MARTINGALA; i++){
            hilosApostadores[i] = new Thread(apostadores[i]);
            hilosApostadores[i].start();
        }
        
        for(int i = 0; i < APOSTADORES + APOSTADORES_MARTINGALA; i++){
            hilosApostadores[i].join();
        }
        
        hiloRuleta.join();
        
    }
    
}
