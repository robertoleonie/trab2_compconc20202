public class Atuador extends Thread {
  int id; // id da thread
  FilaCircular fila; // Fila compartilhada
  LE le; // Monitor
  double media; // acumulador da média de temperaturas
  int contMedia; // contador da média de temperaturas
  int contAlertaAmarelo; // Contador do alerta amarelo
  int contAlertaVermelho; // Contador do alerta vermelho

  Atuador(int id, FilaCircular fila, LE le) {
    this.id = id;
    this.fila = fila;
    this.le = le;
  }

  @Override
  public void run() {
    while(true) {
      // Função do Monitor para gerenciamento da entrada de Leitores
      le.EntraLeitor(this.id);
      System.out.println("Atuador " + this.id + " esta lendo as temperaturas");
      
      // para cada interação zera os valores
      this.contMedia = 0;
      this.media = 0;
      this.contAlertaAmarelo = 0;
      this.contAlertaVermelho = 0;

      // Para cada elemento da fila circular faça enquanto o não chegar ao fim da fila
      int i = fila.inicioDaFila - 1;
      if (i >= -1) {
        do {
          i++;
          // garante que quando chegue ao final do array retorne ao começo
          if (i == fila.tamanhoMaximo) {
            i = 0;
          }
          // Se o valor lido é foi registrado pelo sensor do mesmo Id
          if (this.id == fila.elementosDaFila[i].idSensor) {
            // Adiciona na média
            this.media += this.fila.elementosDaFila[i].valor;
            this.contMedia++;

            // Se a temperatura registra for maior que 35 graus
            if (fila.elementosDaFila[i].valor > 35) {
              // soma no contador do alerta amarelo das ultimas 15 temperaturas
              if (this.contAlertaAmarelo < 15)
                this.contAlertaAmarelo++;
              // adiciona ao contador de alerta vermelho
              this.contAlertaVermelho++;
            } else {
              // Manipula o contador de Alarme amarelo para se basear nas ultimas 15 leituras acima de 35
              if (this.contAlertaAmarelo > 0 && this.contMedia >= 15 )
                this.contAlertaAmarelo--;
              // Reseta o contador vermelho
              this.contAlertaVermelho = 0;
            }

            // Dispara os alertas
            if (this.contAlertaVermelho >= 5) {
              System.out.println("Atuador "+this.id+": ALERTA VERMELHO!!!");
            } else if (this.contAlertaAmarelo >= 5) {
              System.out.println("Atuador "+this.id+": Alerta Amarelo!");
            } else {
              System.out.println("Atuador "+this.id+": Condicao normal.");
            }
          }
        } while (i != fila.finalDaFila );
      // Se não existir itens na fila
      } else {
        System.out.println("Atuador " + this.id + " nao leu nenhuma temperatura acima de 30 graus.");
      }

      // Se o contador for zero, ou seja, se o sensor deste atuador não captou nada acima de 30º
      if (this.contMedia == 0) {
        System.out.println("Media das leituras do Atuador "+ this.id +": 0");
      } else {
        System.out.println(String.format("Temperatura media das leituras do Atuador %d: %.1f", this.id ,this.media/this.contMedia));
      }
      System.out.println("Atuador " + this.id + " terminou!");
      // Chama o método do monitor que gerencia a saida de Leitores
      le.SaiLeitor(this.id);
      try {
        // Coloca a Thread para aguardar 2 segundos
        Thread.sleep(2000);
      } catch (InterruptedException e) {};
      
    }
  }
}