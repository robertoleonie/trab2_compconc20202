// Classe que implementa o monitor do problema Leitores/Escritores
class LE {
  private int leitores;
  private int escritores;  
  
  LE() { 
     this.leitores = 0;
     this.escritores = 0;
  } 
  
  // Classe que gerencia a entrada de um leitor
  public synchronized void EntraLeitor (int id) {
    try { 
      // Enquanto houverem escritores o leitor fica bloqueado
      while (this.escritores > 0) {
         System.out.println ("le.leitorBloqueado("+id+")");
         wait(); 
      }
      this.leitores++;
      System.out.println ("le.leitorLendo("+id+")");
    } catch (InterruptedException e) { }
  }
  
  public synchronized void SaiLeitor (int id) {
     this.leitores--;
     // Se é o ultimo leitor saido desperta escritores 
     if (this.leitores == 0) 
           this.notify();
     System.out.println ("le.leitorSaindo("+id+")");
  }
  
  // Função de entrada para escritores
  public synchronized void EntraEscritor (int id) {
    try { 
      // Se houverem leitores lendo ou escritores escrevendo bloqueia
      while ((this.leitores > 0) || (this.escritores > 0)) {
         System.out.println ("le.escritorBloqueado("+id+")");
         wait();  
      }
      this.escritores++;
      System.out.println ("le.escritorEscrevendo("+id+")");
    } catch (InterruptedException e) { }
  }
  
  // Função que gerencia a saida de um escritor
  public synchronized void SaiEscritor (int id) {
     this.escritores--; 
     // Desperta todas as threads para não ocorrer o problema de Deadlock
     notifyAll();
     System.out.println ("le.escritorSaindo("+id+")");
  }
}
