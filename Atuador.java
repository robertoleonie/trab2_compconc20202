public class Atuador extends Thread {
  int id;
  FilaCircular<Elemento> fila;
  Elemento elemento;
  LE le;
  double media;
  int contMedia;
  int contAlertaAmarelo;
  int contAlertaVermelho;

  Atuador(int id, FilaCircular<Elemento> fila, LE le) {
    this.id = id;
    this.fila = fila;
    this.le = le;
  }

  @Override
  public void run() {
    while(true) {
      le.EntraLeitor(this.id);
      contMedia = 0;
      for (int i = fila.inicioDaFila; i <= fila.finalDaFila; i++) { // me incomda
        if (this.id == fila.elementosDaFila[i].idSensor) {
          this.media += this.fila.elementosDaFila[i].valor;
          this.contMedia++;

          if (fila.elementosDaFila[i].valor > 35) {
           if (this.contAlertaAmarelo < 15) this.contAlertaAmarelo++;
           this.contAlertaVermelho++;
          } else {
            if (this.contAlertaAmarelo > 0) this.contAlertaAmarelo--;
            this.contAlertaVermelho = 0;
          }

          if (this.contAlertaVermelho >= 5) {
            System.out.println("Alerta Vermelho!");
          } else if (this.contAlertaAmarelo >= 15) {
            System.out.println("Alerta Amarelo!");
          } else {
            System.out.println("Condição normal");
          }
        }
      }

      System.out.println("Media das leituras: " + this.media / this.contMedia);
      le.SaiLeitor(this.id);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {};
      System.out.println("Atuador " + this.id + "terminou!");
    }
  }
}