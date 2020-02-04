/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Eugenio Romero Ciudad
 */
public class Cliente implements Constantes {
    
    public static void main(String[] args) throws InterruptedException {
        try {
            for(int i = 0; i < 50; i++){
                Cliente c = new Cliente(Constantes.IP, Constantes.PUERTO);
                c.iniciarComunicacion();
                System.out.printf("\n\n");
                
                Thread.sleep(750);
            }
        } catch (IOException ex) {
            System.out.println("ERROR EN EL CLIENTE");
        }
    }
    
    /*Clase del Cliente*/
    private static int numCliente = 1;
    private String nombreCliente;
    private InetSocketAddress direccion;
    private Socket socket;
    
    public Cliente(String ip, int puerto) throws IOException{
        nombreCliente = "[Cliente "+(numCliente++)+"] ";
        direccion = new InetSocketAddress(ip, puerto);
        socket = new Socket();
    }
    
    public void iniciarComunicacion() throws IOException{
        socket.connect(direccion);
        
        //Defino un flujo de recepcion y otro de envio
        BufferedReader bfr = Utilidades.getBufferedReader(socket.getInputStream());
        PrintWriter pw = Utilidades.getPrintWriter(socket.getOutputStream());
        
        Utilidades.enviarMensaje(pw, Constantes.MSG_INICIAL);
        
        String linea1 = bfr.readLine();
        System.out.println(nombreCliente + linea1);
        int codRespuesta = Integer.MIN_VALUE;
        try{
            codRespuesta = duplicarCodigo(bfr.readLine());
        }catch(NumberFormatException ex){
            System.out.println(nombreCliente + "No se recibió un número");
            socket.close();
            return;
        }
        String trozo1=Constantes.MSG_CODIGO;
        String trozo2=Integer.toString(codRespuesta) ;
        System.out.println(nombreCliente + trozo1);
        System.out.println(nombreCliente + trozo2);
        
        pw.println(trozo1);
        pw.println(trozo2);
        pw.flush();
        System.out.println(nombreCliente + "Bufferes vaciados");
        System.out.println(bfr.readLine());
        System.out.println(nombreCliente + "Leida respuesta del servidor");
        socket.close();
              
    }
    
    public int duplicarCodigo(String linea) throws NumberFormatException{
        System.out.println(nombreCliente + linea); //Imprimo el mensaje recibido
        
        for(int i = 0; i < linea.length(); i++){
            if(Character.isAlphabetic(linea.charAt(i)))
                throw new NumberFormatException();
        }
        
        Random rand = new Random();
        
        int codigo = Integer.parseInt(linea);
        
        if(rand.nextBoolean()) //fuerzo errores
            return codigo * 3;
        
        return codigo * 2;
    }

}
