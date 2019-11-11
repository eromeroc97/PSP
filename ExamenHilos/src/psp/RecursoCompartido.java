/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.util.LinkedList;


/**
 *
 * @author erome
 */
public class RecursoCompartido {
    private int max_elementos;
    private LinkedList<Integer> cola;

    public RecursoCompartido(int max_elementos) {
        this.max_elementos = max_elementos;
        this.cola = new LinkedList<Integer>();
    }
    
    public synchronized boolean estaVacia(){
        return this.cola.size() == 0;
    }
    
    public synchronized boolean estaLlena(){
        return this.cola.size() == this.max_elementos;
    }
    
    public synchronized boolean encolar(int num){
        if(estaLlena())
            return false;
        
        cola.addFirst(num);
        return true;
    }
    
    public synchronized int desencolar(){
        if(estaVacia())
            return -1;
        
        return cola.removeLast();
    }
    
    
    
    
}
