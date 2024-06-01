package banco;

public class CuentaVista extends CuentaBancaria {

  public CuentaVista(long saldo) {
    super(MenuConstantes.CUENTA_VISTA, saldo, 0, 300);
  }

  @Override
  public double calcularIntereses() {
    return intereses;
  }

  @Override
  public double calcularComisiones() {
    return comisiones;
  }

}
