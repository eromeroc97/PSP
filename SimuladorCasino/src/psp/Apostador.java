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
public class Apostador implements Runnable{
    private String nombre;
    private int dinero;
    private Apuesta apuesta;
    private Ruleta ruleta;
    private boolean martingala;
    private int ultimaApuesta;

    public Apostador(int dinero, String nombre, boolean martingala, Ruleta ruleta) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.ultimaApuesta = 10;
        this.martingala = martingala;
        this.ruleta = ruleta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public Apuesta getApuesta() {
        return apuesta;
    }

    public Ruleta getRuleta() {
        return ruleta;
    }

    public boolean getMartingala() {
        return martingala;
    }

    public int getUltimaApuesta() {
        return ultimaApuesta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void setApuesta(Apuesta apuesta) {
        this.apuesta = apuesta;
    }

    public void setRuleta(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public void setMartingala(boolean martingala) {
        this.martingala = martingala;
    }

    public void setUltimaApuesta(int ultimaApuesta) {
        this.ultimaApuesta = ultimaApuesta;
    }
    
    public void esperar(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println("Error en la espera");
        }
    }
    
    @Override
    public void run(){
        while(dinero > 0){
            if(ruleta.getApuestasActivas()){
                ruleta.apostar(this);
            }
            
            esperar();
            
            if(ruleta.getComprobarApuestas()){
                ruleta.comprobar(this);
            }
           
        }
    }
    
}
