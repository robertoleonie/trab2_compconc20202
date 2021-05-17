import java.util.Arrays;

// Objeto que representa um array circular FIFO
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

  // Função que enfilera um Elemento na fila
  public void enfileira(Elemento item) {
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

// Classe caso seja não se possa inserir algo na fila caso ela esteja cheia
// class FilaCheiaException extends RuntimeException {

//   private static final long serialVersionUID = 1L;

//   public FilaCheiaException() {
//     super();
//   }

//   public FilaCheiaException(String message) {
//     super(message);
//   }
// }

// Classe caso não se pode remover algo da fila caso ela esteja vazia
// class FilaVaziaException extends RuntimeException {

//   private static final long serialVersionUID = 1L;

//   public FilaVaziaException() {
//     super();
//   }

//   public FilaVaziaException(String message) {
//     super(message);
//   }
// }

