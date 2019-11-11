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
        Banca banca = new Banca(50000);
        Ruleta ruleta = new Ruleta(banca);
        Apostador[] apostadores = new Apostador[4];
        
        Thread[] hilosApostadores = new Thread[4];
        Thread hiloRuleta = new Thread(ruleta);
        
        for(int i = 0; i < 4; i++){
            apostadores[i] = new Apostador(1000, "Apostador "+(i+1), i%2 == 0, ruleta);
        }
        
        hiloRuleta.start();
        
        for(int i = 0; i < 4; i++){
            hilosApostadores[i] = new Thread(apostadores[i]);
            hilosApostadores[i].start();
        }
        
        for(int i = 0; i < 4; i++){
            hilosApostadores[i].join();
        }
        
        hiloRuleta.join();
        
    }
    
}
