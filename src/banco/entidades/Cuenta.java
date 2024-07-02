package banco.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private String tipoCuenta;
    private List<Transaccion> transacciones;

    public Cuenta(String numeroCuenta, double saldo, String tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.transacciones = new ArrayList<>();
    }

    // sipodemos!!!!!!
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }
}
