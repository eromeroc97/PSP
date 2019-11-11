/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.util.Random;

/**
 *
 * @author erome
 */
public class Cliente implements Runnable{
    private static final int MAX_INTENTOS = 10;
    
    PuertaRC puerta;
    ProductoRC productos;
    String nombre;
    Random generador;

    public Cliente(PuertaRC puerta, ProductoRC productos, String nombre) {
        this.puerta = puerta;
        this.productos = productos;
        this.nombre = nombre;
        this.generador = new Random();
    }
    
    
    
    @Override
    public void run() {
        for(int intento = 1; intento <= MAX_INTENTOS; intento++){
            if(!puerta.estaOcupada()){
                if(puerta.intentarEntrar()){
                    esperar();
                    puerta.liberarPuerta();
                    if(productos.cogerProducto()){
                        System.out.println(this.nombre+": Cogí un producto! (Intento: "+intento+")");
                        return ;
                    }else{
                        System.out.println(this.nombre+": Crucé pero no cogi nada. (Intento: "+intento+")");
                        return ;
                    }
                }
            }else{
                esperar();
            }
        }
        System.out.println(this.nombre+": lo intenté muchas veces y no pude");
    }
    
    public void esperar(){
        try {
            int ms_azar = generador.nextInt(2000);
            Thread.sleep(ms_azar);
        } catch (InterruptedException ex) {
            System.out.println("Fallo en la espera");
        }
    }
    
}
