
package com.iesmaestre;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Peticion implements Runnable{

    private final Socket socket;
    private OutputStream os;
    private InputStream is;
    private PrintWriter pw;
    private BufferedReader bfr;

    Peticion(Socket conexionEntrante) throws IOException {
        this.socket=conexionEntrante;
        this.construirFlujos();
    }

    private void construirFlujos() throws IOException{
        os=socket.getOutputStream();
        is=socket.getInputStream();
        
        pw =Utilidades.getPrintWriter(os);
        bfr=Utilidades.getBufferedReader(is);
    }
    @Override
    public void run() {
        try {
            String linea=this.bfr.readLine();
            while (!linea.equals(Constantes.FIN)){
                if (linea.equals(Constantes.LISTAR)){
                    enviarListadoFicheros();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarListadoFicheros() {
        String directorioFicheros;
        directorioFicheros="C:\\Users\\b14-04m\\Documents\\servidorficheros";
        /* Primero averiguarmos cuantos ficheros
        hay en dicha carpeta y enviamos ese número al cliente        */
        File directorio = new File(directorioFicheros);
        /* Despues, si hay ficheros, averiguamos los nombres
        y vamos enviando los nombres al cliente*/
        File[] listaFicheros = directorio.listFiles();
        /*Enviamos la cantidad de ficheros que hay*/
        pw.println(listaFicheros.length);
        for(File f : listaFicheros){
            String nombreFichero = f.getName();
            pw.println(nombreFichero);
        }
        pw.flush();
    }

}
