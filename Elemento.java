public class Elemento {
  int valor, idSensor, idLeitura = 0;

  public Elemento(int valor, int idSensor, int idLeitura) {
    this.valor = valor;
    this.idSensor = idSensor;
    this.idLeitura = idLeitura;
  }

  public int getValor() {
    return this.valor;
  }

  public int getIdSensor() {
    return this.idSensor;
  }

  public int getIdLeitura() {
    return this.idLeitura;
  }
}
