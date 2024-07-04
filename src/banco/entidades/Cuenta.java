package banco.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cuenta{
    private String numero;
    private double saldo;
    private List<String> transacciones;

    public Cuenta(String numero, double saldo){
        this.numero = numero;
        this.saldo = saldo;
        this.transacciones = new ArrayList<>();
    }

    public String getNumero(){
        return numero;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public List<String> getTransacciones(){
        return transacciones;
    }

    public void agregarTransaccion(String transaccion){
        transacciones.add(transaccion);
    }
}