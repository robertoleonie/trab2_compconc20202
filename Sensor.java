import java.util.Random;

// Classe dos Sensores
public class Sensor extends Thread{
  // Intervalo de temperaturas aceito pelos Sensores
  public static final int MIN_TEMPERATURA = 25; 
  public static final int MAX_TEMPERATURA = 40;

  int id; // id da thread
  FilaCircular fila; // Fila compartilhada
  Elemento elemento; // Elemento que sera criado pelo Sensor
  LE le; // Monitor

  Random random = new Random();
  int contMedia = 0;
  int valorLido;

  public Sensor(int id, FilaCircular fila, LE le) {
    this.id = id;
    this.fila = fila;
    this.le = le;
  }

  @Override
  public void run(){
    while(true) {
      // Chama função do monitor de entrada de escritores
      le.EntraEscritor(this.id);
      System.out.println("Sensor " + this.id + " esta registrando a temperatura");

      // Gera uma temperatura dentro do intervalo aceito
      this.valorLido = random.nextInt(MAX_TEMPERATURA - MIN_TEMPERATURA) + MIN_TEMPERATURA;
      System.out.println("Sensor " + this.id + " registrou a temperatura: "+ this.valorLido);

      // Se o valor for acima de 30 Cria o elemento e enfilera
      if (this.valorLido > 30) {
        elemento = new Elemento(this.valorLido, this.id, this.contMedia++);
        fila.enfileira(elemento);
      }

      System.out.println("Sensor " + this.id + " terminou!");
      // Chama a função do monitor de saida de Escritores
      le.SaiEscritor(this.id);
      
      try {
        // Coloca a thread para aguardar 1 segundo
        Thread.sleep(1000);
      } catch (InterruptedException e) {};
    }
  }
}
