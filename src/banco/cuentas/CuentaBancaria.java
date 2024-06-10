package banco.cuentas;

import banco.MenuConstantes;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class CuentaBancaria implements TipoCuenta {
  protected String tipoCuenta; // tipo de cuenta que está usando
  protected long saldo; // saldo inicial al crear la cuenta
  protected double intereses; // porcentaje que se le ofrece por usar la cuenta
  protected double comisiones; // porcentaje que se le cobra por usar la cuenta

  private String fechaHora; // fecha y hora de la transacción
  private String numeroCuenta; // numero de cuenta

  Random random = new Random();

  protected CuentaBancaria(String tipoCuenta, long saldo, double intereses, double comisiones) {
    this.numeroCuenta = generarNumeroCuenta(); // se le asigna un numero random
    this.tipoCuenta = tipoCuenta;
    this.saldo = saldo;
    this.intereses = intereses;
    this.comisiones = comisiones;
    this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss"));
  }

  public String formatearTransaccion(String tipoTransaccion, double monto, String nombreCliente, String rutCliente) {
    LocalDateTime ahora = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");
    fechaHora = ahora.format(formatter);

    return String.format("[%s || %.2f || %s %s || %s || %s || %s]",
            tipoTransaccion, monto, tipoCuenta, numeroCuenta, nombreCliente, rutCliente, fechaHora);
  }

  private String generarNumeroCuenta() {
    // Generar un número de cuenta aleatorio de 9 dígitos
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      sb.append(random.nextInt(10)); // Genera un dígito aleatorio entre 0 y 9
    }
    return sb.toString();
  }

  public long depositar(long monto){
    saldo += monto;
    return saldo;
  }

  public double retirar(long monto) {
    // Se verifica que el saldo sea suficiente para el retiro
    if (monto <= saldo) {
      // Se retira el monto especificado
      saldo -= monto;
      // Se aplica la comisión por uso de la cuenta
      saldo -= calcularComisiones();
      System.out.println(MenuConstantes.RETIRO_EXITOSO);
    } else {
      System.out.println(MenuConstantes.SALDO_INSUFICIENTE);
    }
    return saldo;
  }

  public String toString() {
    return "[CuentaBancaria]\n" +
            "Tipo de Cuenta = " + tipoCuenta + '\n' +
            "Numero de Cuenta = " + numeroCuenta + '\n' +
            "Saldo Inicial = " + saldo + '\n' +
            "Intereses = " + intereses + '\n' +
            "Comisiones = " + comisiones + '\n';
  }

  // getter y setter lo heredaran las clases hijas
  public String getNumeroCuenta() {
    return numeroCuenta;
  }

  public void setNumeroCuenta(String numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  public String getTipoCuenta() {
    return tipoCuenta;
  }

  public void setSaldo(long saldo) {
    this.saldo = saldo;
  }

  public long getSaldo() {
    return saldo;
  }

  public void setFechaHora(String fechaHora) {
    this.fechaHora = fechaHora;
  }

  public String getFechaHora() {
    return fechaHora;
  }
}