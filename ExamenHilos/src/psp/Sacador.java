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
public class Sacador implements Runnable{
    
    RecursoCompartido shared;
    int sacados;

    public Sacador(RecursoCompartido shared) {
        this.shared = shared;
        this.sacados = 0;
    }

    public int getSacados() {
        return sacados;
    }    

    @Override
    public void run() {
        int num;
        while(true){
            num = shared.desencolar();
            if(num != -1){
                System.out.println("El sacador sacó: "+num);
                this.sacados++;
            }
        }
    }
    
}
