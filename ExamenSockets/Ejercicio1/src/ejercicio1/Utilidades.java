package ejercicio1;




import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;


public class Utilidades {
    /**
     * Obtiene un flujo de entrada que nos permite trabajar con líneas de texto
     * @param is Flujo de entrada simple 
     * @return 
     */
    public static BufferedReader
        getBufferedReader(InputStream is){
            InputStreamReader isr=
                    new InputStreamReader(is);
            BufferedReader bfr=
                    new BufferedReader(isr);
            return bfr;
    }
        
        public static void getVersion(){
            System.out.println("Soy la version 1.0");
        }
        
    /**
     * Obtiene un flujo de salida que nos permite trabajar con líneas de texto
     * @param os Flujo de salida simple 
     * @return 
     */
    public static PrintWriter
            getPrintWriter(OutputStream os){
                OutputStreamWriter osw=new OutputStreamWriter(os);
                PrintWriter pw= new PrintWriter(osw);
                return pw;
                        
    }
            
        
    /**
     * Envía un mensaje a través de un flujo de salida garantizando
     * que se vuelca el buffer al instante
     * @param pw Flujo de salida a través del cual se envía el mensaje
     * @param linea Mensaje que se desea enviar
     */
    public static void enviarMensaje(PrintWriter pw, String linea){
        pw.println(linea);
        pw.flush();
    }
    
    
    /**
     * Obtiene un flujo de datos de tipo DataInputStream que nos permite
     * trabajar indistintamente con tipos simples o complejos
     * @param s Socket del cual se quiere obtener un DataInputStream
     * @return Un DataInputStream
     * @throws IOException 
     */
    public static DataInputStream getDataInputStream(Socket s) throws IOException{
        InputStream inputStream = s.getInputStream();
        return new DataInputStream(inputStream);
    }
    
    /**
     * Obtiene un flujo de datos de tipo DataOutputStream que nos permite
     * trabajar indistintamente con tipos simples o complejos
     * @param s Socket del cual se quiere obtener un DataOutputStream
     * @return Un DataOutputStream
     * @throws IOException 
     */
    public static DataOutputStream getDataOutputStream(Socket s) throws IOException{
        OutputStream outputStream = s.getOutputStream();
        return new DataOutputStream(outputStream);
    }
    
    
    public static int getNumBytesALeer(int tamBuffer, long recuentoParcial, long totalALeer){
        int numBytes=0;
        long bytesPendientes=totalALeer - recuentoParcial;
        System.out.println("Tam buffer:"+tamBuffer+" recuentoParcial:"+recuentoParcial+" total:"+totalALeer);
        if (bytesPendientes<tamBuffer){
            System.out.println("Hay que leer:"+bytesPendientes);
            return (int) bytesPendientes;
        }
        else {
            System.out.println("Hay que leer:"+tamBuffer);
            return tamBuffer;
        }
    }
    /**
     * Recibe un fichero enviado a través de un socket, confiando en 
            que el emisor primero envía la longitud de dicho fichero 
     * @param socket Socket a través del cual se va a recibir un fichero
     * @param rutaFichero Ruta completa donde se va a guardar el fichero recibido
     * @param tamanoBuffer Tamaño de buffer de recepcion con el que se desea trabajar
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void recibirFichero(Socket socket, String rutaFichero, int tamanoBuffer) throws FileNotFoundException, IOException{
        byte[] buffer=new byte[tamanoBuffer];
        
        DataInputStream flujoReceptor=new DataInputStream(socket.getInputStream());
        FileOutputStream ficheroRecibido=new FileOutputStream(rutaFichero);
        
        /*Primero esperamos a que se nos diga cuantos bytes ocupa el fichero*/
        long totalBytesParaRecibir=flujoReceptor.readLong();
        System.out.println("El emisor ha notificado el tamaño del fichero:"+
                totalBytesParaRecibir+" bytes");
        /*Y aquí se recibe el fichero bloque a bloque*/
        long contadorBytesRecibidos=0;
        
        /* No podemos leer todos los bytes que queramos, es importante 
        "no pasarnos" y leer solo hasta donde nos corresponda. Si nos excedemos
        leeremos datos que no nos corresponden y estropearemos el envio */
        int cuantosBytesHayQueLeer=Utilidades.getNumBytesALeer(
                tamanoBuffer, contadorBytesRecibidos, totalBytesParaRecibir);
        /*Ahora que sabemos cuantos bytes hay que leer leemos esos bytes*/
        int numBytesLeidos = flujoReceptor.read(buffer, 0, cuantosBytesHayQueLeer);
        /*Y tomamos nota de cuntos bytes llevamos leídos*/
        contadorBytesRecibidos=numBytesLeidos;
        while (contadorBytesRecibidos != totalBytesParaRecibir){
            
            ficheroRecibido.write(buffer, 
                    0, numBytesLeidos);
            cuantosBytesHayQueLeer=Utilidades.getNumBytesALeer(
                tamanoBuffer, contadorBytesRecibidos, totalBytesParaRecibir);
            numBytesLeidos = flujoReceptor.read(buffer, 0, cuantosBytesHayQueLeer);
            if (numBytesLeidos==-1){
                break;
            }
            contadorBytesRecibidos+=numBytesLeidos;
        } /*Fin del while*/
        /*Nos aseguramos de volcar el último buffer, que quizá no esté completo*/
        if (numBytesLeidos!=-1){
            ficheroRecibido.write(buffer, 
                    0, numBytesLeidos);
        }
        /*Cerramos el fichero y salimos*/
        ficheroRecibido.close();
    }
    
    
    /**
     * Este método envía un fichero a través de un socket indicando primero el número
     * total de bytes que se van a enviar a fin de que el receptor sepa exactamente
     * cuantos bytes leer
     * @param socket Socket a través del cual se va a enviar el fichero
     * @param rutaFichero Ruta completa del fichero que se desea enviar
     * @param tamanoBuffer Tamaño de buffer de emisión con el que se desea trabajar
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void enviarFichero(Socket socket, String rutaFichero, int tamanoBuffer) throws IOException {
        /*Averiguamos el tamaño del fichero*/
        File fichero=new File (rutaFichero);
        long bytesFichero = fichero.length();
        
        DataOutputStream flujoEmision=new DataOutputStream(socket.getOutputStream());
        FileInputStream  ficheroParaEnviar=new FileInputStream(rutaFichero);

        /*Enviamos la longitud del fichero*/
        flujoEmision.writeLong(bytesFichero);

        System.out.println("Notificando al receptor el tamaño del fichero:"+
                bytesFichero + " bytes");
        byte[] buffer=new byte[tamanoBuffer];        

        /*Aquí se envia el fichero bloque a bloque*/
        int numBytesLeidos = ficheroParaEnviar.read(buffer);
        while (numBytesLeidos >0){
            flujoEmision.write(buffer, 0, numBytesLeidos);
            System.out.println("Enviados "+numBytesLeidos+" bytes");
            numBytesLeidos = ficheroParaEnviar.read(buffer);
        }
        /*Y nos aseguramos de vaciar todos los búferes y salir*/
        flujoEmision.flush();
        ficheroParaEnviar.close();
    }
    
    /**
     * Espera un tiempo al azar 
     * @param msComoMaximo Tiempo que se debe esperar como máximo en milisegundos
     */
    public static void esperarTiempoAlAzar (int msComoMaximo){
        Random random=new Random();
        int milisegundos = random.nextInt(msComoMaximo);
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
            System.out.println("ERROR:Ha fallado la espera "
                    + "con una excepción InterruptedException");
        }
    }
}
