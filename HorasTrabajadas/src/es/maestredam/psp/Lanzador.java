/*
En un cierto departamento existen procesos que actualizan el numero de horas
trabajadas por cada empleado. en un momento dado puede haber muchos actualizadores
que operen sobre las horas de trabajo de un mismo empleado. se pide crear un programa
que simule la operativa de los procesos de actualizacion de dichas horas sobre los
distintos objetos empleado, granatizando que dos o mas actualizadores que accedan
a la vez a un mismo empleado hacen los cambios de manera consistente.
 */

package es.maestredam.psp;

/**
 *
 * @author Eugenio Romero Ciudad
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int NUM_ACTUALIZADORES = 30;
        
        Empleado e = new Empleado(0);
        
        Thread[] hilos = new Thread[NUM_ACTUALIZADORES];
        for(int i = 0; i < NUM_ACTUALIZADORES; i++){
            Actualizador a = new Actualizador(e, 8);
            hilos[i] = new Thread(a);
            hilos[i].start();
        }
        
        for(int i = 0; i < NUM_ACTUALIZADORES; i++)
            hilos[i].join();
        
        System.out.println(e.getHoras());
    }

}
