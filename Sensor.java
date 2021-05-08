import java.util.Queue;
import java.util.Random;

public class Sensor {
  public static final int MIN_TEMPERATURA = 25; 
  public static final int MAX_TEMPERATURA = 40;
  public static final int NUM_ELEMENTOS = 60;

  int id;
  Queue<Elemento> fila;
  Elemento elemento;
  Escritor escritor = new Escritor();
  Random random = new Random();
  double media = 0;
  int contMedia = 0;

  public Sensor(int id, Queue<Elemento> fila) {
    this.id = id;
    this.fila = fila;
  }

  public void medicao() {
    while(true) {
      escritor.iniciaEscrita(this.id);
      System.out.println("Sensor " + this.id + " esta registrando a temperatura");
      int valorLido = (random.nextInt() % (MAX_TEMPERATURA - MIN_TEMPERATURA + 1)) + MIN_TEMPERATURA;
      
      this.media += valorLido;
      this.contMedia++;

      if (fila.size() > 60) {
        // Fila circular
      }

      if (valorLido > 30) {
        elemento = new Elemento(valorLido, this.id, elemento.idLeitura++);
        fila.add(elemento);
      }

      escritor.terminaEscrita(this.id);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {};
    }

    this.media = this.media / this.contMedia;
    System.out.println("Temperatura Media do Sensor: "+id+" foi: "+ media);
  }

  
}
