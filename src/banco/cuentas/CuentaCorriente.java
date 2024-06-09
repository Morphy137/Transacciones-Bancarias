package banco.cuentas;


import banco.MenuConstantes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CuentaCorriente extends CuentaBancaria{
  // intereses = 0.01
  // comisiones = 0.2
  private LocalDate ultimaFechaInteres;
  private LocalDate ultimaFechaComisiones;

  public CuentaCorriente() {
    super(MenuConstantes.CUENTA_CORRIENTE, 1000000, 0.2f, 0.2);
  }

  @Override
  public double calcularIntereses() {
    LocalDate fechaActual = LocalDate.now();
    long diasTrascurridos = ChronoUnit.DAYS.between(ultimaFechaInteres, fechaActual);
    double interesesGenerados = saldo * intereses * (diasTrascurridos/365.0);
    ultimaFechaInteres = fechaActual; // Actualiza la ultima fecha de comisiiones
    return interesesGenerados;
  }

  @Override
  public double calcularComisiones() {
    LocalDate fechaActual = LocalDate.now();
    long mesesTranscurridos = ChronoUnit.MONTHS.between(ultimaFechaComisiones, fechaActual);
    double comisionesGeneradas = comisiones * mesesTranscurridos;
    ultimaFechaComisiones = fechaActual; // Actualiza la ultima fecha de comisiones
    return comisionesGeneradas;
  }
}
