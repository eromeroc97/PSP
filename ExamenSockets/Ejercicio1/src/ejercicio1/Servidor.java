/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugenio Romero Ciudad
 */
public class Servidor implements Constantes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Servidor s = new Servidor();        
        
    }
    
    public Servidor(){
        try {
            ServerSocket server = new ServerSocket(Constantes.PUERTO);
            System.out.println("Ejecutando servidor...");
            while(true){
                Socket conexion = server.accept();
                Resolver solucionador = new Resolver(conexion);
                Thread hilo = new Thread(solucionador);
                hilo.start();
            }
            
        } catch (IOException ex) {
            System.out.println("NO SE HA PODIDO CREAR SERVICIO EN EL PUERTO: "+Constantes.PUERTO);
        }
    }
    
    private class Resolver implements Runnable, Constantes{
        
        private Socket sock;
        
        public Resolver(Socket conexion){
            sock = conexion;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("*** Conexion recibida ***");
                ejecutar();
            } catch (IOException ex) {
                System.out.println("ERROR EN LA RESOLUCION DEL HILO");
            }
        }
        
        public void ejecutar() throws IOException{
            //Creo un flujo de entrada y otro de salida
            BufferedReader bfr = Utilidades.getBufferedReader(sock.getInputStream());
            PrintWriter pw = Utilidades.getPrintWriter(sock.getOutputStream());
            
            String linea1=bfr.readLine();
            System.out.println("Iniciada comunicacion cliente:"+linea1);
            if(linea1.equals(Constantes.MSG_INICIAL)){
                pw.println(Constantes.MSG_BIENVENIDO);
                Random rand = new Random();
                int val = -1;
                if(rand.nextBoolean()){
                    val = rand.nextInt(Constantes.LIMITE_RANDOM);
                    pw.println(Integer.toString(val));
                }else{
                    pw.println("ABC");
                }
                pw.flush();
                
                String linea2=bfr.readLine();
                if(linea2 == null){
                    System.out.println("El cliente cerro la conexion");
                    return;
                }else{
                    System.out.println("Recibido del cliente:"+linea2);
                }
                
                
                if(linea2.equals(Constantes.MSG_CODIGO)){
                    System.out.println("*** Codigo Recibido ***");
                    if(Integer.parseInt(bfr.readLine()) == val * 2){
                        pw.println(Constantes.MSG_OK);
                    }else{
                        pw.println(Constantes.MSG_FALLO);
                    }
                }
                pw.flush();
            }
        }
        
    }
}
