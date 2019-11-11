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
public class Ruleta implements Runnable{
    private Banca banca;
    
    private boolean apuestasActivas;
    private boolean comprobarApuestas;
    private int extraido;

    public Ruleta(Banca banca) {
        this.banca = banca;
        this.apuestasActivas = true;
        this.comprobarApuestas = false;
        this.extraido = -1;
    }
    
    public void cambiarEstadoApuestas(){
        this.apuestasActivas = !this.apuestasActivas;
    }
    
    public void cambiarEstadoComprobacion(){
        this.comprobarApuestas = !this.comprobarApuestas;
    }

    public boolean getApuestasActivas() {
        return apuestasActivas;
    }

    public boolean getComprobarApuestas() {
        return comprobarApuestas;
    }
    
    
    
    public void esperar(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println("Error en la espera");
        }
    }
    
    public synchronized void comprobar(Apostador a){
        if(a.getApuesta().getNumero() == this.extraido){
            banca.movimiento(a.getApuesta().getCantidad());
            a.setDinero(a.getDinero() + a.getApuesta().getCantidad());
        }
    }
    
    public synchronized boolean apostar(Apostador a){
        Apuesta apuesta;
        if(a.getDinero() >= a.getUltimaApuesta()){
            apuesta = new Apuesta(a.getUltimaApuesta());
            a.setDinero(a.getDinero() - apuesta.getCantidad());
            if(a.getMartingala()){
                a.setUltimaApuesta(a.getUltimaApuesta() * 2);
            }
            System.out.println(a.getNombre()+": Apuesto "+apuesta.getCantidad()+" al numero "+apuesta.getNumero());
            banca.movimiento(apuesta.getCantidad());
            a.setApuesta(apuesta);
            return true;
        }else{
            System.out.println(a.getNombre()+": no puedo apostar (DINERO: "+a.getDinero()+" )");
            return false;
        }
    }
    
    @Override
    public void run(){
        while(banca.getDinero() > 0){
            System.out.println("Dinero Banca (PREVIO): "+banca.getDinero());
            //las apuestas estan activas
            esperar();
            cambiarEstadoApuestas();//apuestas a false
            extraer();
            cambiarEstadoComprobacion(); //comprobacion a true
            esperar();
            cambiarEstadoComprobacion(); //comprobacion a false
            
            cambiarEstadoApuestas(); //apuestas a true
            
        }
    }

    private void extraer() {
        Random rand = new Random();
        this.extraido = rand.nextInt(37); //entre 0 y 36
        System.out.println("Ruleta: se ha extraido el numero "+extraido);
    }
    
}
