package banco.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
  private final String nombre;
  private final String rut;
  private final List<Cuenta> cuentas;

  public Cliente(String nombre, String rut) {
    this.nombre = nombre;
    this.rut = rut;
    this.cuentas = new ArrayList<>();
  }

  // constructor
  public String getNombre() {
    return nombre;
  }

  public String getRut() {
    return rut;
  }

  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void agregarCuenta(Cuenta cuenta) {
    this.cuentas.add(cuenta);
  }

}
