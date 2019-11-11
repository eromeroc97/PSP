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
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        final int MAX_CLIENTES = 300;
        final int MAX_PRODUCTOS = 100;

        Cliente[] clientes = new Cliente[MAX_CLIENTES];
        PuertaRC puerta = new PuertaRC();
        ProductoRC productos = new ProductoRC();

        productos.añadirProductos(MAX_PRODUCTOS);

        Thread[] hilosClientes = new Thread[MAX_CLIENTES];

        for(int i = 0; i < MAX_CLIENTES; i++){
            String nombreHilo = "Cliente "+(i+1);
            clientes[i] = new Cliente(puerta, productos, nombreHilo);

            hilosClientes[i] = new Thread(clientes[i]);

            hilosClientes[i].start();

        }

        for(int i=0; i< MAX_CLIENTES; i++)
            hilosClientes[i].join();

    }
    
}
