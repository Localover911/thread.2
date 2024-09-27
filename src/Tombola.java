import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Tombola extends Thread {
    private static int[] disponibili = new int[90];
    private static int disponibiliVirtuale = 90;
    private static Random randomizzatore = new Random();
    private String nome;
    public Tombola(String n) {
        for (int i = 0;i<disponibiliVirtuale;i++) {
            disponibili[i] = i+1;
        }
        nome = n;
    }
    private int genera() {
        int numeroCasuale = randomizzatore.nextInt(disponibiliVirtuale);
        return numeroCasuale;
    }
    public int estrai() {
        if (disponibiliVirtuale == 0) {
            return -1;
        } else {
            int numero = genera();
            int estratto = disponibili[numero];
            disponibili[numero] = disponibili[disponibiliVirtuale-1];
            disponibiliVirtuale--;
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e) {
            System.out.println(e);
        }
        return estratto;
        }
    }
    public void run() {
        for (int i = 0; i<91; i++) {
            int ris = estrai();
            if(ris == -1) {
                System.out.println(nome + " ha finito i numeri da estrarre.");
                break;
            } else {
                System.out.println(nome + " ha estratto " + ris);
            }
        }
    }
}