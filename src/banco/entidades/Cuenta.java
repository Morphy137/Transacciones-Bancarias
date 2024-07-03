package banco.entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    private final List<Transaccion> transacciones;

    public Cuenta() {
        this.transacciones = new ArrayList<>();
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }
}
