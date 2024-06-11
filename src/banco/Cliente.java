package banco;

import banco.cuentas.CuentaBancaria;

import java.util.List;
import java.util.ArrayList;

public class Cliente{
  private final String nombre;
  private final String edad;
  private final String rut;
  private final String direccion;
  private final String telefono;
  private final String email;
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
    System.out.println(this);

    System.out.println("\n---[Cuentas]---\n");
    for (CuentaBancaria cuenta : cuentas) {
      System.out.println(cuenta.toString()); // Llama al método toString() de cada cuenta
    }
  }

  public String toString() {
    return "---[Cliente]---\n" +
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

  public String getRut() {
    return rut;
  }
}
