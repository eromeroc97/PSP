
package com.iesmaestre;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public void servir() throws IOException{
        ServerSocket socket=new ServerSocket(
                Constantes.PUERTO_FICHEROS);
        while (true){
            Socket conexionEntrante = socket.accept();
            Peticion p=new Peticion(conexionEntrante);
            Thread hiloAsociado=
                    new Thread(p);
            hiloAsociado.start();
        }
    }
    
    public static void main(String[] args) throws IOException{
        Servidor s = new Servidor();
        s.servir();
    }
}
