
package com.iesmaestre.servidordiccionario;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Cliente {
    final String ipServidor="127.0.0.1";
    final int    puerto    =Constantes.PUERTO_SERVICIO;
    public void chatear() throws IOException{
        Socket conexion;
        InetSocketAddress direccionServidor;
        direccionServidor=
           new InetSocketAddress(ipServidor, puerto);
        conexion=new Socket();
        conexion.connect(direccionServidor);
        PrintWriter pw=
           Utilidades.getPrintWriter(
                   conexion.getOutputStream());
    
        BufferedReader bfr=
          Utilidades.getBufferedReader(
                  conexion.getInputStream()
            );
        
        Console consola = System.console();
        String lineaLeida=consola.readLine();
        while (!lineaLeida.equals("/fin")){
            
        }
    }
    
    
    
    public static void main(String[] args) throws IOException{
        Cliente c=new Cliente();
        
        c.chatear();
    }
}
