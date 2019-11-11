/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package psp;

import java.util.Random;

/**
 *
 * @author Eugenio Romero ciudad
 */
public class Escritor implements Runnable{
    
    private static final int MAX_INTENTOS = 100;
    
    private void esperar(){
        try{
            Thread.sleep(generador.nextInt(401)+100); //401 porque solo se incluye hasta 400 
            //+ 100 convierte el rango [0, 400] en [100, 500]
        }catch(InterruptedException ex){
            System.out.println("Error en la espera");
        }
    }
    
    @Override
    public void run(){
        int intento = 0;
        while(intento < MAX_INTENTOS){
            int numeroEscribir = generador.nextInt(300);
            int posicionEscrita = memoria.almacenar(numeroEscribir); //valor maximo 300
            if(posicionEscrita > -1){ //ha logrado escribir
                System.out.println(nombre+" he escrito en posicion "+posicionEscrita);
                esperar();
                int numeroLeido = memoria.leer(posicionEscrita);
                System.out.println(nombre+" valor leido: "+numeroLeido+" -> Correcto: "+(numeroLeido == numeroEscribir));
                memoria.liberar(posicionEscrita);
            }
        }
    }
    
    private MemoriaCompartida memoria;
    private String nombre;
    private Random generador;

    public Escritor(MemoriaCompartida memoria, String nombre) {
        this.memoria = memoria;
        this.nombre = nombre;
        this.generador = new Random();
    }
    
    public String getName(){return this.nombre;}
    

}
