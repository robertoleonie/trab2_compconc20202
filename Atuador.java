public class Atuador extends Thread {
  int id;
  FilaCircular fila;
  Elemento elemento;
  LE le;
  double media;
  int contMedia;
  int contAlertaAmarelo;
  int contAlertaVermelho;

  Atuador(int id, FilaCircular fila, LE le) {
    this.id = id;
    this.fila = fila;
    this.le = le;
  }

  @Override
  public void run() {
    while(true) {
      le.EntraLeitor(this.id);
      System.out.println("Atuador " + this.id + " esta lendo as temperaturas");
      this.contMedia = 0;
      this.media = 0;
      this.contAlertaAmarelo = 0;
      this.contAlertaVermelho = 0;
      int i = fila.inicioDaFila - 1;
      if (i >= -1) {
        do {
          i++;
          if (i == fila.tamanhoMaximo) {
            i = 0;
          }
          if (this.id == fila.elementosDaFila[i].idSensor) {
            this.media += this.fila.elementosDaFila[i].valor;
            this.contMedia++;

            if (fila.elementosDaFila[i].valor > 35) {
              if (this.contAlertaAmarelo < 15)
                this.contAlertaAmarelo++;
              this.contAlertaVermelho++;
            } else {
              if (this.contAlertaAmarelo > 0)
                this.contAlertaAmarelo--;
              this.contAlertaVermelho = 0;
            }

            if (this.contAlertaVermelho >= 5) {
              System.out.println("Atuador "+this.id+": ALERTA VERMELHO!!!");
            } else if (this.contAlertaAmarelo >= 5) {
              System.out.println("Atuador "+this.id+": Alerta Amarelo!");
            } else {
              System.out.println("Atuador "+this.id+": Condicao normal.");
            }
          }
        } while (i != fila.finalDaFila );
      } else {
        System.out.println("Atuador " + this.id + " nao leu nenhuma temperatura acima de 30 graus.");
      }
      if (this.contMedia == 0) {
        System.out.println("Media das leituras do Atuador "+ this.id +": 0");
      } else {
        System.out.println(String.format("Temperatura media das leituras do Atuador %d: %.1f", this.id ,this.media/this.contMedia));
      }
      System.out.println("Atuador " + this.id + " terminou!");
      le.SaiLeitor(this.id);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {};
      
    }
  }
}