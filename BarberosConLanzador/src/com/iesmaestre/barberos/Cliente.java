
package com.iesmaestre.barberos;


public class Cliente implements Runnable{
    GestorSillas gestorSillas;
    private boolean afeitado;

    public Cliente(GestorSillas gestorSillas) {
        this.gestorSillas = gestorSillas;
    }

    @Override
    public void run() {
        int posSillaLibre=gestorSillas.getNumSillaLibre();
        if (posSillaLibre==GestorSillas.TODAS_OCUPADAS){
            System.out.println("Barberia completa, me marcho");
        } else {
            while (!afeitado){
                
            } /*Fin del while*/
            System.out.println("Estoy afeitado, me marcho");
        } /*Fin del else*/
    } /*Fin del run*/
    

} /*Fin de la clase Cliente*/
