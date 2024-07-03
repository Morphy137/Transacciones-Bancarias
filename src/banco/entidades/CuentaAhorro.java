package banco.entidades;

public class CuentaAhorro extends Cuenta{
  private double tasaInteres;

  public CuentaAhorro() {
    super();
    this.tasaInteres = 0.05;
  }

  public double getTasaInteres() {
    return tasaInteres;
  }

  public void setTasaInteres(double tasaInteres) {
    this.tasaInteres = tasaInteres;
  }
}
