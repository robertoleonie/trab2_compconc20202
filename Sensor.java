import java.util.Random;

public class Sensor extends Thread{
  public static final int MIN_TEMPERATURA = 25; 
  public static final int MAX_TEMPERATURA = 40;
  public static final int NUM_ELEMENTOS = 60;

  int id;
  FilaCircular<Elemento> fila;
  Elemento elemento;
  LE le;
  Random random = new Random();
  // double media = 0;
  int contMedia = 0;
  int valorLido;

  public Sensor(int id, FilaCircular<Elemento> fila, LE le) {
    this.id = id;
    this.fila = fila;
    this.le = le;
  }

  @Override
  public void run() {
    elemento = new Elemento(this.valorLido, this.id, this.contMedia);
    while(true) {
      le.EntraEscritor(this.id);
      System.out.println("Sensor " + this.id + " esta registrando a temperatura");
      this.valorLido = (random.nextInt() % (MAX_TEMPERATURA - MIN_TEMPERATURA + 1)) + MIN_TEMPERATURA;

      if (this.valorLido > 30) {
        elemento.valor = this.valorLido;
        elemento.idLeitura = this.contMedia++;

        fila.enfileira(elemento);
      }

      le.SaiEscritor(this.id);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {};
      System.out.println("Sensor " + this.id + "terminou!");
    }

    // this.media = this.media / this.contMedia;
    // System.out.println("Temperatura Media do Sensor: "+ this.id +" foi: " + this.media);
  }
}
