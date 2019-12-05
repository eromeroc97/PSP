
package com.iesmaestre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Cliente {
    Socket socket=null;
    PrintWriter pw;
    BufferedReader bfr;
    InputStream is;
    OutputStream os;
    private void construirFlujos() throws IOException{
        os=socket.getOutputStream();
        is=socket.getInputStream();
        
        pw =Utilidades.getPrintWriter(os);
        bfr=Utilidades.getBufferedReader(is);
    }
    public void establecerConexion() throws IOException{
        InetSocketAddress direccion;
        direccion=new InetSocketAddress("127.0.0.1",
                Constantes.PUERTO_FICHEROS);
        socket=new Socket();
        socket.connect(direccion);
        construirFlujos();
    }
    
    private void enviarLinea(String mensaje){
        this.pw.println(mensaje);
        this.pw.flush();
    }
    public void consultar() throws IOException{
        this.enviarLinea(Constantes.LISTAR);
        String numFicheros=this.bfr.readLine();
        int cantidadFicheros = Integer.parseInt(numFicheros);
        for (int i=0; i<cantidadFicheros; i++){
            String nombreFichero = this.bfr.readLine();
            System.out.println(nombreFichero);
        }
        
    }
    
    public void enviar(String rutaFichero){
        
    }
    
    public void recibir(String nombreFichero){
        
    }
    
    public void test() throws IOException{
        this.establecerConexion();
        this.consultar();
    }
    
    public static void main(String[] argumentos) throws IOException{
        Cliente c = new Cliente();
        c.test();
    }
}
