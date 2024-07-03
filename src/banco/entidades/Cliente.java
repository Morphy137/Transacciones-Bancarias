package banco.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
  private final String username;
  private final String password;
  private final String nombre;
    private final String rut;
  private final List<Cuenta> cuentas;

  public Cliente(String username, String password, String nombre, String rut) {
    this.username = username;
    this.password = password;
    this.nombre = nombre;
      this.rut = rut;
    this.cuentas = new ArrayList<>();
  }

  // consrtuctor
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

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
