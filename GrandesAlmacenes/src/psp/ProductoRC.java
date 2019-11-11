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
public class ProductoRC {
    private LinkedList listaProductos;

    public ProductoRC() {
        this.listaProductos = new LinkedList<>();
    }
    
    public void añadirProductos(int max){
        for(int i = 1; i <= max; i++)
            listaProductos.add(i);
    }
    
    public boolean cogerProducto(){
        if(listaProductos.isEmpty())
            return false;
        
        listaProductos.removeLast();
        return true;
    }
    
}
