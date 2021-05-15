public class Main {
  public static final int MAX_DIM = 60;
  public static void main (String[] args) {
    if (args.length != 2) {
      System.out.println(args[0] + " <num Leitores e Atuadores>");
      System.exit(-1);
    }

    int nThreads = Integer.parseInt(args[1]);
    Thread[] threads = new Thread[2*nThreads];
    FilaCircular<Elemento> fila = new FilaCircular<>(MAX_DIM);
    LE le = new LE();

    for (int i=0; i < nThreads; i++) {
      threads[i] = new Sensor(i, fila, le);
      threads[i].start();
    }

    for (int i=0; i < nThreads; i++) {
      threads[i+nThreads] = new Atuador(i, fila, le);
      threads[i+nThreads].start();
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
