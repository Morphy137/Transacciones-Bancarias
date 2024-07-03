package banco.entidades;

public class CuentaVista extends Cuenta{
  private double comision;

  public CuentaVista() {
    super();
    this.comision = 0.01;
  }

  public double getComision() {
    return comision;
  }

  public void setComision(double comision) {
    this.comision = comision;
  }
}
