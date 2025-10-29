package U2_Multihilo._00b_RetirarDineroCuenta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CajeroAutomatico implements Runnable {

  CuentaBancaria micuenta = new CuentaBancaria();

  @Override
  public void run() {

    for (int i = 0; i < 5; i++) {
      retirarDineroCuenta(10);
      if (micuenta.getSaldoActual() < 0) {
        System.out.println(
            "Don " + Thread.currentThread().getName() + " Su cuenta posee saldo negativo!!!");
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        Logger.getLogger(CajeroAutomatico.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private void retirarDineroCuenta(int cantidad) {
    if (micuenta.getSaldoActual() >= cantidad) {
      System.out.println(
          Thread.currentThread().getName()
              + " se dispone a retirar dinero. Saldo Actual ="
              + micuenta.getSaldoActual());
      System.out.println(
          "El usuario = "
              + Thread.currentThread().getName()
              + " está realizando una operación de retirada de "
              + cantidad
              + "€");
      micuenta.retirarDineroCuenta(cantidad);
      System.out.println(
          "Operación realizada por "
              + Thread.currentThread().getName()
              + " correctamente. El nuevo saldo es "
              + micuenta.getSaldoActual()
              + "€");

    } else {
      System.out.println(
          "Don "
              + Thread.currentThread().getName()
              + " usted no tiene dinero suficiente en la cuenta corriente para realizar la operación");
    }
  }
}
