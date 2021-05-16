class LE {
  private int leitores;
  private int escritores;  
  
  // Construtor
  LE() { 
     this.leitores = 0;
     this.escritores = 0;
  } 
  
  public synchronized void EntraLeitor (int id) {
    try { 
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
     if (this.leitores == 0) 
           this.notify();
     System.out.println ("le.leitorSaindo("+id+")");
  }
  
  // Entrada para escritores
  public synchronized void EntraEscritor (int id) {
    try { 
      while ((this.leitores > 0) || (this.escritores > 0)) {
         System.out.println ("le.escritorBloqueado("+id+")");
         wait();  
      }
      this.escritores++;
      System.out.println ("le.escritorEscrevendo("+id+")");
    } catch (InterruptedException e) { }
  }
  
  public synchronized void SaiEscritor (int id) {
     this.escritores--; 
     notifyAll();
     System.out.println ("le.escritorSaindo("+id+")");
  }
}
