/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.maestredam.psp;

/**
 *
 * @author Eugenio Romero ciudad
 */
public class Sumador implements Runnable{
    
    private final int limiteInferior;
    private final int limiteSuperior;
    private int resultado;
    
    public Sumador(int limiteInferior, int limitesuperior) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limitesuperior;
    }

    public int getResultado() {
        return resultado;
    }
    
    @Override
    public void run() {
        resultado = 0;
        for(int i = limiteInferior; i <= limiteSuperior; i++){
            resultado += i;
        }
    }
    
    
}
