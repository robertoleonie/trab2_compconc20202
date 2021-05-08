public class Leitor {
  int leitores = 0;
  Escritor escritor = new Escritor();

  public synchronized void iniciaLeitura(int id) {
    System.out.println("Thread " + id + "quer ler");
    while(escritor.escritores > 0) {
      System.out.println("Thread Leitora "+ id + "bloqueou");
      try {
        this.wait();
      } catch (InterruptedException e) {};
      
      System.out.println("Thread Leitora "+ id + " desbloqueou");
    }
    this.leitores++;
  }

  public synchronized void terminaLeitura(int id) {
    System.out.println("Thread "+ id + " terminou de ler.");
	  this.leitores--;
	  if (this.leitores == 0) {
      escritor.notify();
    }
  }
}
