package banco.entidades;

import java.time.LocalDateTime;

public class Transaccion {
  private String tipo;
  private String nroCuenta;
  private double monto;
  private String cliente;
  private String rut;
  private LocalDateTime fechaTransaccion;


  public Transaccion(String tipo, String nroCuenta, double monto, String cliente, String rut, LocalDateTime fechaTransaccion) {
    this.tipo = tipo;
    this.nroCuenta = nroCuenta;
    this.monto = monto;
    this.cliente = cliente;
    this.rut = rut;
    this.fechaTransaccion = fechaTransaccion;
  }

  // Getters
  public String getTipo() {
    return tipo;
  }

  public String getNroCuenta() {
    return nroCuenta;
  }

  public double getMonto() {
    return monto;
  }

  public String getCliente() {
    return cliente;
  }

  public String getRut() {
    return rut;
  }

  public LocalDateTime getFechaTransaccion() {
    return fechaTransaccion;
  }


  // Setters
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setNroCuenta(String nroCuenta) {
    this.nroCuenta = nroCuenta;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public void setRut(String rut) {
    this.rut = rut;
  }

  public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
    this.fechaTransaccion = fechaTransaccion;
  }
}