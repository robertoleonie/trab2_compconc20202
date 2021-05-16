public class Main {
  public static final int MAX_DIM = 60;
  public static void main (String[] args) {
    if (args.length != 1) {
      System.out.println("Digite: java Main <numSensores>");
      System.exit(-1);
    }

    int nThreads = Integer.parseInt(args[0]);
    Thread[] threads = new Thread[2*nThreads];
    FilaCircular fila = new FilaCircular(MAX_DIM);
    LE le = new LE();

    // Inicia o log de saida para Python
    System.out.println ("import verificaLE");
    System.out.println ("le = verificaLE.LE()");

    for (int i=0; i < nThreads; i++) {
      threads[i] = new Sensor(i+1, fila, le);
      threads[i].setPriority(Thread.NORM_PRIORITY); // Prioridade máxima para threads escritoras
    }

    for (int i=0; i < nThreads; i++) {
      threads[i+nThreads] = new Atuador(i+1, fila, le);
      threads[i+nThreads].setPriority(Thread.MAX_PRIORITY); // Prioridade menor para threads leitoras
    }

    for (int i=0; i < 2* nThreads; i++) {
      threads[i].start();
    }

    for (int i=0; i< 2* nThreads; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}
