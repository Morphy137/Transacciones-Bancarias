package banco.cuentas;

import banco.MenuConstantes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CuentaAhorro extends CuentaBancaria {
  private LocalDate ultimaFechaInteres;
  private LocalDate ultimaFechaComisiones;
  private LocalDate fechaActual;

  public CuentaAhorro(long saldo) {
    super(MenuConstantes.CUENTA_AHORRO, saldo, 0.02, 0.0);
    this.ultimaFechaComisiones = LocalDate.now();
    this.ultimaFechaInteres = LocalDate.now();
    this.fechaActual = LocalDate.now();
  }

  @Override
  public double calcularIntereses() {
    long diasTrascurridos = ChronoUnit.DAYS.between(ultimaFechaInteres, fechaActual);
    double interesesGenerados = saldo * intereses * (diasTrascurridos / 365.0);
    ultimaFechaInteres = fechaActual; // Actualiza la ultima fecha de comisiones
    return interesesGenerados;
  }

  @Override
  public double calcularComisiones() {
    long mesesTranscurridos = ChronoUnit.MONTHS.between(ultimaFechaComisiones, fechaActual);
    double comisionesGeneradas = comisiones * mesesTranscurridos;
    ultimaFechaComisiones = fechaActual; // Actualiza la ultima fecha de comisiones
    return comisionesGeneradas;
  }
}
