package com.iesmaestre.psp.chat;

public class Mensaje {
    public enum TipoMensaje{
        ESTABLECIMIENTO_NICK, MENSAJE_PUBLICO, 
        MENSAJE_PRIVADO, MENSAJE_ERRONEO
    }
    private final String NICK       =   "NICK";
    private final String PUBLICO    =   "PUBL";
    private final String PRIVADO    =   "PRIV";
    
    private TipoMensaje tipoMensaje;
    private String      destinatarioMensaje;
    private String      textoMensaje;
    private String      nickEstablecido;
    
    private final String      cadenaCompletaRecibida;
    
    
    /* Se asume que todo mensaje tiene la misma estructura
       /NICK pepito ----->Para cambiar el nick
       /PUBL Hola gente-->Para enviar un mensaje a todo el mundo
       /PRIV pepito ¿Qué tal?-->Para enviar un mensaje privado a alguien
    */
    public Mensaje(String cadenaRecibida){
        this.cadenaCompletaRecibida=cadenaRecibida;
        /*Primero miramos el primer caracter*/
        if (cadenaRecibida.charAt(0)!='/'){
            /* Si no es un / es que hay un error*/
            this.tipoMensaje=TipoMensaje.MENSAJE_ERRONEO;
            return;
        }
        /*Ahora examinamos los caracteres 1-4 para ver si es
        un NICK un PUBL o un PRIV*/
        String tipoMensajeRecibido=cadenaRecibida.substring(1,5);
        
        /*Comprobando si es un /NICK*/
        if (tipoMensajeRecibido.equals(NICK)){
            this.destinatarioMensaje    =null;
            this.nickEstablecido        =this.getRestoMensaje(
                    cadenaRecibida);
            this.tipoMensaje            =TipoMensaje.ESTABLECIMIENTO_NICK;
            return ;
        } /*Fin del if*/
        
        /*Comprobando si es un /PUBL*/
        if (tipoMensajeRecibido.equals(PUBLICO)){
            this.destinatarioMensaje    =null;
            this.textoMensaje           =this.getRestoMensaje(
                    cadenaRecibida);
            this.tipoMensaje            =TipoMensaje.MENSAJE_PUBLICO;
            return ;
        } /*Fin del if*/
        
        
        if (tipoMensajeRecibido.equals(PRIVADO)){
            this.rellenarCamposMensajePrivado(cadenaRecibida);
            this.tipoMensaje            =TipoMensaje.MENSAJE_PRIVADO;
            return ;
        }
        
        /* Si se llega aquí es porque no hemos encontrado
        un mensaje con una estructura válida. Marcamos
        el error y salimos*/
        this.tipoMensaje    =   TipoMensaje.MENSAJE_ERRONEO;
        
    }
    
    private String getRestoMensaje(String cadenaRecibida){
        return cadenaRecibida.substring(6, cadenaRecibida.length());
    }
    
    private void rellenarCamposMensajePrivado(String cadenaRecibida){
        /*Troceamos el mensaje*/
        String[] trozos = cadenaRecibida.split(" ");
        /*El elemento 0 es el mensaje y el 1 el nick destinatario*/
        this.destinatarioMensaje=trozos[1];
        
        /*Y extraemos el trozo de cadena que va más allá
        del tipo de mensaje y del nick. Añadimos 1 por la /,
        1 por el espacio entre /PRIV y el nick y otro
        1 por el espacio entre el nick y el mensaje*/
        int posMensaje=1+PRIVADO.length()+1+destinatarioMensaje.length()+1;
        this.textoMensaje=
                cadenaRecibida.substring(posMensaje, 
                        cadenaRecibida.length());
    }
    
    /*Getters*/

    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    public String getDestinatarioMensaje() {
        return destinatarioMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public String getNickEstablecido() {
        return nickEstablecido;
    }
    
    public String getCadenaCompletaRecibida() {
        return cadenaCompletaRecibida;
    }
    
    /*Fin de los getters*/

    
}
