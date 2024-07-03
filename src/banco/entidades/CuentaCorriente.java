package banco.entidades;

public class CuentaCorriente extends Cuenta{
  private double limiteSobregiro;

  public CuentaCorriente() {
    super();
    this.limiteSobregiro = 1000.00;
  }

  public double getLimiteSobregiro() {
    return limiteSobregiro;
  }

  public void setLimiteSobregiro(double limiteSobregiro) {
    this.limiteSobregiro = limiteSobregiro;
  }
}
