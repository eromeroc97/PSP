/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package psp;

/**
 *
 * @author Eugenio Romero ciudad
 */
public class MemoriaCompartida {
    private int TAMANO_MEMORIA;
    private int[] memoria;
    private boolean[] ocupadas;

    public MemoriaCompartida(int TAMANO_MEMORIA) {
        this.TAMANO_MEMORIA = TAMANO_MEMORIA;
        this.memoria = new int[TAMANO_MEMORIA];
        this.ocupadas = new boolean[TAMANO_MEMORIA];
    }
    
    public synchronized int leer(int posicion){
        return memoria[posicion];
    }
    
    public synchronized void liberar(int posicion){
        ocupadas[posicion] = false;
    }
    
    public synchronized int almacenar(int num){
        int pos;
        for(pos = 0; pos < TAMANO_MEMORIA; pos++){
            if(estaLibre(pos)){
                ocupadas[pos] = true;
                memoria[pos] = num;
                return pos;
            }
        }
        return -1;
    }
    
    public boolean estaLibre(int pos){
        return !ocupadas[pos];
    }
    
    
    
}
