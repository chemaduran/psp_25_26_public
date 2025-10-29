package U2_Multihilo._00b_RetirarDineroCuenta;

public class CuentaBancaria {

  private int saldoActual = 50;

  public int getSaldoActual() {
    return this.saldoActual;
  }

  public void retirarDineroCuenta(int cantidad) {
    saldoActual -= cantidad;
  }
}
