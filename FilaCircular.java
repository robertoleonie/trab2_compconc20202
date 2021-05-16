import java.util.Arrays;

public class FilaCircular {
  int tamanhoAtual;
  Elemento[] elementosDaFila;
  int tamanhoMaximo;

  int finalDaFila;
  int inicioDaFila;      

  public FilaCircular(int tamanhoMaximo) {
    this.tamanhoMaximo = tamanhoMaximo;
    elementosDaFila = new Elemento[tamanhoMaximo];
    tamanhoAtual = 0;
    inicioDaFila = -1;
    finalDaFila = -1;
  }

  public void enfileira(Elemento item) throws FilaCheiaException {
      finalDaFila = (finalDaFila + 1) % elementosDaFila.length;
      elementosDaFila[finalDaFila] = item;
      tamanhoAtual++;
          
      if (inicioDaFila == -1) {
        inicioDaFila = finalDaFila;
      }

      if (finalDaFila == inicioDaFila && tamanhoAtual > 1) {
        inicioDaFila = (finalDaFila + 1) % elementosDaFila.length;
      }
    // }
  }

  
  // public Elemento desenfileira() throws FilaVaziaException {
  //   Elemento elementoDesenfileirado;
  //   if (estaVazia()) {
  //     throw new FilaVaziaException("Circular Queue is empty. Element cannot be retrieved");
  //   } else {
  //     elementoDesenfileirado = elementosDaFila[inicioDaFila];
  //     elementosDaFila[inicioDaFila] = null;
  //     inicioDaFila = (inicioDaFila + 1) % elementosDaFila.length;
  //     tamanhoAtual--;
  //   }
  //   return elementoDesenfileirado;
  // }

  public boolean estaCheia() {
    return (tamanhoAtual == elementosDaFila.length);
  }

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

