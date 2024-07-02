package banco.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
  private String username;
  private String password;
  private String nombre;
  private String edad;
  private String rut;
  private List<Cuenta> cuentas;

  public Cliente(String username, String password, String nombre, String edad, String rut) {
    this.username = username;
    this.password = password;
    this.nombre = nombre;
    this.edad = edad;
    this.rut = rut;
    this.cuentas = new ArrayList<>();
  }

  // consrtuctor
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEdad() {
    return edad;
  }

  public void setEdad(String edad) {
    this.edad = edad;
  }

  public String getRut() {
    return rut;
  }

  public void setRut(String rut) {
    this.rut = rut;
  }

  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void agregarCuenta(Cuenta cuenta) {
    this.cuentas.add(cuenta);
  }
}
