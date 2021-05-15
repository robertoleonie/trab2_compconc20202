import java.util.Arrays;

public class FilaCircular<T> {
  int tamanhoAtual; //Current Circular Queue Size
  T[] elementosDaFila;
  int tamanhoMaximo; //Circular Queue maximum size

  int finalDaFila; //finalDaFila position of Circular queue(new element enqueued at finalDaFila).
  int inicioDaFila; //inicioDaFila position of Circular queue(element will be dequeued from inicioDaFila).      

  public FilaCircular(int tamanhoMaximo) {
    this.tamanhoMaximo = tamanhoMaximo;
    elementosDaFila = (T[]) new Object[this.tamanhoMaximo];
    tamanhoAtual = 0;
    inicioDaFila = -1;
    finalDaFila = -1;
  }

  /**
   * Enqueue elements to finalDaFila.
   */
  public void enfileira(T item) throws FilaCheiaException {
    // if (estaCheia()) {
    //   throw new FilaCheiaException("Circular Queue is full. Element cannot be added");
    // } else {
      finalDaFila = (finalDaFila + 1) % elementosDaFila.length;
      elementosDaFila[finalDaFila] = item;
      tamanhoAtual++;
          
      if (inicioDaFila == -1) {
        inicioDaFila = finalDaFila;
      }
    // }
  }

  /**
   * Dequeue element from Front.
   */
  public T desenfileira() throws FilaVaziaException {
    T elementoDesenfileirado;
    if (estaVazia()) {
      throw new FilaVaziaException("Circular Queue is empty. Element cannot be retrieved");
    } else {
      elementoDesenfileirado = elementosDaFila[inicioDaFila];
      elementosDaFila[inicioDaFila] = null;
      inicioDaFila = (inicioDaFila + 1) % elementosDaFila.length;
      tamanhoAtual--;
    }
    return elementoDesenfileirado;
  }

  /**
   * Check if queue is full.
   */
  public boolean estaCheia() {
    return (tamanhoAtual == elementosDaFila.length);
  }

  /**
   * Check if Queue is empty.
   */
  public boolean estaVazia() {
    return (tamanhoAtual == 0);
  }

  @Override
  public String toString() {
    return "FilaCircular [" + Arrays.toString(elementosDaFila) + "]";
  }

}

class FilaCheiaException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public FilaCheiaException() {
    super();
  }

  public FilaCheiaException(String message) {
    super(message);
  }
}

class FilaVaziaException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public FilaVaziaException() {
    super();
  }

  public FilaVaziaException(String message) {
    super(message);
  }
}

