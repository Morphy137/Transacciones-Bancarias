package banco;

import banco.cuentas.CuentaBancaria;

import java.util.List;
import java.util.ArrayList;

public class Cliente{
  private String nombre;
  private String edad;
  private String rut;
  private String direccion;
  private String telefono;
  private String email;
  private List<CuentaBancaria> cuentas;

  public Cliente(String nombre, String edad, String rut, String direccion, String telefono, String email) {
    this.nombre = nombre;
    this.edad = edad;
    this.rut = rut;
    this.direccion = direccion;
    this.telefono = telefono;
    this.email = email;
    this.cuentas = new ArrayList<>();
  }

  //agregar una cuenta al cliente
  public void agregarCuenta(CuentaBancaria cuenta){
    cuentas.add(cuenta);
  }

  //obtener las cuentas del cliente
  public List<CuentaBancaria> getCuentas(){
    return cuentas;
  }

  //buscar las cuentas que tiene el cliente
  public CuentaBancaria obtenerCuentaPorTipo(String tipoCuenta) {
    for (CuentaBancaria cuenta : cuentas) {
      if (cuenta.getTipoCuenta().equals(tipoCuenta)) {
        return cuenta;
      }
    }
    return null;
  }

  //muestra los datos del cliente y sus cuentas
  public void mostrarInformacionCliente(){
    System.out.println(this.toString());

    System.out.println("\n----[Cuentas]----\n");
    for (CuentaBancaria cuenta : cuentas) {
      System.out.println(cuenta.toString()); // Llama al método toString() de cada cuenta
    }
  }

  public String toString() {
    return "[Cliente]\n" +
            "Nombre = " + nombre + '\n' +
            "Edad = " + edad + '\n' +
            "RUT = " + rut + '\n' +
            "Dirección = " + direccion + '\n' +
            "Teléfono = " + telefono + '\n' +
            "Email = " + email + '\n';
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

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
