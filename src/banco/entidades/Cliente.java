package banco.entidades;

import java.util.HashMap;
import java.util.Map;

public class Cliente {
  private final String nombre;
  private final String rut;
  private final Map<String, Cuenta> cuentas;

  public Cliente(String nombre, String rut) {
    this.nombre = nombre;
    this.rut = rut;
    this.cuentas = new HashMap<>();
  }

  // constructor
  public String getNombre() {
    return nombre;
  }

  public String getRut() {
    return rut;
  }

  public Map<String, Cuenta> getCuentas() {
    return cuentas;
  }

  public void agregarCuenta(String tipo, Cuenta cuenta){
    if(!cuentas.containsKey(tipo)){
      cuentas.put(tipo, cuenta);
    }
  }
}
