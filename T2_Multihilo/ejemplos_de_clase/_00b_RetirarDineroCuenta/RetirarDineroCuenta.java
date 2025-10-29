package U2_Multihilo._00b_RetirarDineroCuenta;

public class RetirarDineroCuenta {

  public static void main(String[] args) {
    CajeroAutomatico cajero = new CajeroAutomatico();
    Thread pepito = new Thread(cajero, "Pepito");
    Thread juanito = new Thread(cajero, "Juanito");

    pepito.start();
    juanito.start();
  }
}
