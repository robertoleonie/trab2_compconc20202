// Função Main
public class Main {
  // Define tamanho fixo da fila
  public static final int MAX_DIM = 60;
  public static void main (String[] args) {
    // Verifica se entrou com a quantidade certa de argumentos
    if (args.length != 1) {
      System.out.println("Digite: java Main <numSensores>");
      System.exit(-1);
    }

    // converte o argumento para inteiro
    int nThreads = Integer.parseInt(args[0]);
    // Inicia as variaveis utilizadas
    Thread[] threads = new Thread[2*nThreads];
    FilaCircular fila = new FilaCircular(MAX_DIM);
    LE le = new LE();

    // Inicia o log de saida para Python
    System.out.println ("import verificaLE");
    System.out.println ("le = verificaLE.LE()");

    // Cria as threads Sensores
    for (int i=0; i < nThreads; i++) {
      threads[i] = new Sensor(i+1, fila, le);
      threads[i].setPriority(Thread.NORM_PRIORITY); // Prioridade máxima para threads escritoras
    }

    // Cria as threads Atuadores
    for (int i=0; i < nThreads; i++) {
      threads[i+nThreads] = new Atuador(i+1, fila, le);
      threads[i+nThreads].setPriority(Thread.MAX_PRIORITY); // Prioridade menor para threads leitoras
    }

    // Inicializa todas as Threads
    for (int i=0; i < 2* nThreads; i++) {
      threads[i].start();
    }

    // Aguarda todas as Threads
    for (int i=0; i< 2* nThreads; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}
