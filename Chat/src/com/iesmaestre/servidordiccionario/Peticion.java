
package com.iesmaestre.servidordiccionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Peticion implements Runnable{
    private Socket socketConCliente;
    private BufferedReader bfr;
    private PrintWriter pw;
    
    private int MAX_CADENAS;
    private ArrayList<String> cadenas;
    
    private boolean flujosConstruidos=false;
    private boolean parametrosLeidos=false;
    
    
    
    
    public Peticion(Socket socketCliente) {
        this.socketConCliente = socketCliente;
        this.cadenas=new ArrayList();
    }     

    private void construirFlujos(){
        try {
            InputStream is = 
                    this.socketConCliente.getInputStream();
            OutputStream os=
                    this.socketConCliente.getOutputStream();
            this.bfr=Utilidades.getBufferedReader(is);
            this.pw =Utilidades.getPrintWriter(os);
        } catch (IOException ex) {
            this.flujosConstruidos=false;
            return ;
        }
        /* Si llegamos aquí es porque ha sido
        posible construir los flujos */
        this.flujosConstruidos=true;
    }
    private void leerParametros(){
        try{
            String strNumCadenas=this.bfr.readLine();
            MAX_CADENAS=Integer.parseInt(strNumCadenas);
            for(int i=0; i<MAX_CADENAS; i++){
                String linea=this.bfr.readLine();
                this.cadenas.add(linea);
            }
        }catch (IOException e){
            System.out.println("Error en lectura"
                    + "de parametros del cliente");
            this.parametrosLeidos=false;
            return ;
        }
        this.parametrosLeidos=true;
    }
    private void mostrarErrorFlujos(){
        System.out.println("No fue posible "
         + "construir los flujos debido quizá a "
         + "un error de red. Conexión terminada.");
        return ;
    }
    @Override
    public void run() {
        this.construirFlujos();
        if (!this.flujosConstruidos){
            this.mostrarErrorFlujos();
            return ;
        }
        this.leerParametros();
        if (!this.parametrosLeidos){
            return ;
        }
        /* Completar el próximo día*/
        String mayor="";
        for (int pos1=0; pos1<cadenas.size(); pos1++){
            String cadena1=this.cadenas.get(pos1);
            int codigoComparacion;
            codigoComparacion=cadena1.compareTo(mayor);
            if (codigoComparacion>0){
                mayor=cadena1;
            }
        }
        pw.println(mayor);
        pw.flush();
        
        
    }

}
