package com.iesmaestre.barberos;


public class GestorSillas {
    private boolean[] sillasLibres;
    private boolean[] clienteEstaAtendido;
    private int MAX_SILLAS_LIBRES;
    public static final int TODAS_OCUPADAS      =   -1;
    public static final int NADIE_QUE_ATENDER   =   -2;
    private int siguienteSillaQueExaminar;
    public GestorSillas(int MAX_SILLAS_LIBRES) {
        this.MAX_SILLAS_LIBRES = MAX_SILLAS_LIBRES;
        this.sillasLibres=new boolean[MAX_SILLAS_LIBRES];
        this.clienteEstaAtendido=new boolean[MAX_SILLAS_LIBRES];
        for (int i=0; i<MAX_SILLAS_LIBRES; i++){
            this.sillasLibres[i]=true;
            this.clienteEstaAtendido[i]=false;
        }
        this.siguienteSillaQueExaminar=0;
    }
    
    public synchronized int getNumSillaLibre(){
        for (int i=0; i<this.MAX_SILLAS_LIBRES; i++){
            if (this.sillasLibres[i]){
                this.sillasLibres[i]=false;
                return i;
            } /*Fin del if*/
        } /*Fin del for*/
        /* Si el bucle termina y llega hasta aquí
        es porque no hay ninguna silla libre, 
        devolvemos un código de error*/
        return TODAS_OCUPADAS;
    } /* Fin del getNumSillasLibres*/
    

    public synchronized int getNumSillaParaAtender(){
         int pos=-1;
         boolean salir;
         int i;
         salir=false;
         i=this.siguienteSillaQueExaminar;
         while(!salir){
            if (
                (this.sillasLibres[i]==false) &&
                (this.clienteEstaAtendido[i]==false)
            ){
                this.clienteEstaAtendido[i]=true;
                this.siguienteSillaQueExaminar= 
                        (i+1) % this.MAX_SILLAS_LIBRES;
                return i;

            } //Fin del if
            i++;
            if (i==this.MAX_SILLAS_LIBRES){
                    i=0;
            }
            if (i==this.siguienteSillaQueExaminar) salir=true;

         } /*Fin del while*/
         return pos;
    } /*Fin del método getNumSillaParaAtender*/
} //Fin de la clase
