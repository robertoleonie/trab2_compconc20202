public class Escritor {
  int escritores = 0;
  Leitor leitor = new Leitor();

  public synchronized void iniciaEscrita(int id) {
    System.out.println("Thread " + id + " quer escrever");
    while(leitor.leitores > 0 || this.escritores > 0) {
      System.out.println("Thread Escritora "+ id + " bloqueou");
      try {
        this.wait();
      } catch (InterruptedException e) {};
      System.out.println("Thread Escritora "+ id + " desbloqueou");
    }
    this.escritores++;
  }

  public synchronized void terminaEscrita(int id) {
    System.out.println("Thread "+ id + " terminou de escrever.");
	  this.escritores--;
	  this.notify();
	  leitor.notifyAll();
  }
}
