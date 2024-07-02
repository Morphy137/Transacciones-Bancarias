package banco.entidades;

import java.time.LocalDateTime;

public class Transaccion {
    private String tipo;
    private double monto;
    private LocalDateTime fechaTransaccion;
    private String cliente;

    public Transaccion(String tipo, double monto, LocalDateTime fechaTransaccion, String cliente) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaTransaccion = fechaTransaccion;
        this.cliente = cliente;
    }

    // bob construye
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
