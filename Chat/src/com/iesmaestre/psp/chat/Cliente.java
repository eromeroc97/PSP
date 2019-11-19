
package com.iesmaestre.psp.chat;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


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
        
        Scanner Teclado = new Scanner(System.in);
        System.out.print("> ");
        String lineaLeida=Teclado.nextLine();
        while (!lineaLeida.equals("/fin")){
            Mensaje msg = new Mensaje(lineaLeida);
            pw.println(lineaLeida);
            pw.flush();
            System.out.print("> ");
            lineaLeida=Teclado.nextLine();
        }
    }
    
    
    
    public static void main(String[] args) throws IOException{
        Cliente c=new Cliente();
        
        c.chatear();
    }
}
