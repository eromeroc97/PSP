/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filosofoscomensales;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugenio Romero ciudad
 */
public class Filosofo implements Runnable{
    private Random generador;

    public Filosofo() {
        this.generador = new Random();
    }
    @Override
    public void run() {
        while(true){
            comer();
            pensar();
        }
    }
    
    private void esperarTiempo(int msMaximo){
        int msElegidos = generador.nextInt(msMaximo);
        try {
            Thread.sleep(msElegidos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void comer() {
        System.out.println("Filosofo comiendo...");
    }

    private void pensar(){
        System.out.println("Filosofo pensando...");
    }

}
