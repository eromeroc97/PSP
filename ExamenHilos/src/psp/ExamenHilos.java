package psp;

public class ExamenHilos {

    public static void main(String[] args) throws InterruptedException {
        final int MAX_SACADORES = 3;
        final int MAX_METEDORES = 10;
        final int MAX_ELEMENTOS = 30;
        
        Thread[] hilosSacadores = new Thread[MAX_SACADORES];
        Thread[] hilosMetedores = new Thread[MAX_METEDORES];
        
        RecursoCompartido sharedResource = new RecursoCompartido(MAX_ELEMENTOS);
        
        /*Construimos los productores*/
        for (int i=0; i<MAX_METEDORES; i++){
                Metedor productor=new Metedor(sharedResource);
                hilosMetedores[i]=new Thread(productor);
                hilosMetedores[i].start();
        }
        /*Construimos los consumidores*/
        for (int i=0; i<MAX_SACADORES; i++){
                Sacador consumidor=new Sacador(sharedResource);
                hilosSacadores[i]=new Thread(consumidor);
                hilosSacadores[i].start();
        }
        
        for (int i=0; i<MAX_METEDORES; i++){
            hilosMetedores[i].join();
        }
        for (int i=0; i<MAX_SACADORES; i++){
            hilosSacadores[i].join();
        }
    }
    
}
