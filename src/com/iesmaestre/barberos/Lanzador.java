
package com.iesmaestre.barberos;


public class Lanzador {
    public static void main() throws InterruptedException{
        final int MAX_SILLAS=4;
        final int MAX_CLIENTES=20000;
        final int MAX_BARBEROS=2;
        
        Thread[] hilosClientes;
        Thread[] hilosBarberos;
        
        hilosBarberos=new Thread[MAX_BARBEROS];
        hilosClientes=new Thread[MAX_CLIENTES];
        
        GestorSillas gestorSillas=new GestorSillas(MAX_SILLAS);
        for (int i=0; i<MAX_BARBEROS; i++){
            Barbero barbero =new Barbero(gestorSillas);
            Thread  hilo    =new Thread(barbero);
            hilosBarberos[i]=hilo;
            hilosBarberos[i].start();
        } /*Fin del for que crea barberos*/
        
        for (int i=0; i<MAX_CLIENTES; i++){
            Cliente cliente =new Cliente(gestorSillas);
            Thread  hilo    =new Thread(cliente);
            hilosClientes[i]=hilo;
            hilosClientes[i].start();
        } /*Fin del for que crea clientes*/
        
        
        /* Esperamos a que acaben los clientes...*/
        for (int i=0; i<MAX_CLIENTES; i++){
            hilosClientes[i].join();
        }
        for (int i=0; i<MAX_BARBEROS; i++){
            hilosBarberos[i].interrupt();
        }
        
        
        
        
    } /*Fin del main*/
} /*Fin de la clase Lanzador*/
