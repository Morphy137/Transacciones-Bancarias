package banco;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Banco{
  private Map<String, Cliente> totalClientes = new HashMap<>(); // Diccionario para almacenar los clientes

  long monto;
  String nombreTitular;

  public Banco(){
    // Constructor vacio
  }

  public void crearCuenta(Scanner scanner){
    // Menú crear cuenta
    System.out.println(MenuConstantes.NOMBRE_BANCO);
    System.out.println(MenuConstantes.NOMBRE_CLIENTE);

    nombreTitular = scanner.nextLine();

    // Crear un nuevo cliente o buscar uno existente
    Cliente cliente = totalClientes.get(nombreTitular);
    if (cliente == null) {
      // Si el cliente no existe, crear uno nuevo
      System.out.println(MenuConstantes.EDAD_CLIENTE);
      String edad = scanner.nextLine();
      System.out.println(MenuConstantes.RUT_CLIENTE);
      String rut = scanner.nextLine();
      System.out.println(MenuConstantes.DIRECCION_CLIENTE);
      String direccion = scanner.nextLine();
      System.out.println(MenuConstantes.TELEFONO_CLIENTE);
      String telefono = scanner.nextLine();
      System.out.println(MenuConstantes.EMAIL_CLIENTE);
      String email = scanner.nextLine();

      cliente = new Cliente(nombreTitular, edad, rut, direccion, telefono, email);
      totalClientes.put(nombreTitular, cliente);
    }

    System.out.println(MenuConstantes.TIPO_CUENTA);
    System.out.println(MenuConstantes.MENU_CREAR_CUENTA);

    int tipoCuenta = scanner.nextInt();
    CuentaBancaria cuenta = null;
    switch (tipoCuenta){
      case 1:
        cuenta = new CuentaCorriente();
        break;
      case 2:
        cuenta = new CuentaAhorro(0);
        break;
      case 3:
        cuenta = new CuentaVista(0);
        break;
      default:
        System.out.println(MenuConstantes.DATO_NOVALIDO);
        break;
    }

    cliente.agregarCuenta(cuenta);

    // Formatear los datos de la transacción
    String informacionTransaccion = cuenta.formatearTransaccion("Creación de cuenta", 0, cliente.getNombre(), cliente.getRut());

    // Simular errores para guardar en el archivo
    SimularError.simularRandomError(cuenta, cliente);

    // Guardar los datos de la transacción en el archivo
    try {
      FileManager.escribirEnArchivo(informacionTransaccion);
    } catch (IOException e) {
      System.err.println("Error al escribir en el archivo: " + e.getMessage());
    }
  }

  public void realizarDeposito(Scanner scanner){
    System.out.println(MenuConstantes.NOMBRE_CLIENTE);
    nombreTitular = scanner.nextLine();

    System.out.println(MenuConstantes.TIPO_CUENTA);
    System.out.println(MenuConstantes.MENU_CREAR_CUENTA);
    String tipoCuenta = scanner.nextLine();
    switch (tipoCuenta){
      case "1":
        tipoCuenta = MenuConstantes.CUENTA_CORRIENTE;
        break;
      case "2":
        tipoCuenta = MenuConstantes.CUENTA_AHORRO;
        break;
      case "3":
        tipoCuenta = MenuConstantes.CUENTA_VISTA;
        break;
      default:
        System.out.println(MenuConstantes.DATO_NOVALIDO);
        break;
    }

    System.out.println(MenuConstantes.MONTO_DEPOSITO);
    monto = scanner.nextLong();

    // Buscar al cliente en el mapa totalClientes
    Cliente cliente = totalClientes.get(nombreTitular);
    if (cliente != null) {
      // Si el cliente existe, obtener la cuenta por tipo
      CuentaBancaria cuenta = cliente.obtenerCuentaPorTipo(tipoCuenta);
      if (cuenta != null) {
        // Si la cuenta existe, depositar el monto
        double nuevoSaldo = cuenta.depositar(monto);
        System.out.println("Depósito realizado con éxito.\nSaldo actual: $" + nuevoSaldo);

        // Formatear los datos de la transacción
        String informacionTransaccion = cuenta.formatearTransaccion("Depósito", monto, cliente.getNombre(), cliente.getRut());

        // Simular errores para guardar en el archivo
        SimularError.simularRandomError(cuenta, cliente);

        // Guardar los datos de la transacción en el archivo
        try {
          FileManager.escribirEnArchivo(informacionTransaccion);
        } catch (IOException e) {
          System.err.println(MenuConstantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
        }

      } else {
        System.out.println(MenuConstantes.CUENTA_NOEXISTE);
      }
    } else {
      // Si el cliente no existe, mostrar un mensaje de error
      System.out.println(MenuConstantes.CLIENTE_NOEXISTE);
    }
  }

  public void realizarRetiro(Scanner scanner){
    System.out.println(MenuConstantes.NOMBRE_CLIENTE);
    nombreTitular = scanner.nextLine();

    System.out.println(MenuConstantes.TIPO_CUENTA);
    System.out.println(MenuConstantes.MENU_CREAR_CUENTA);
    String tipoCuenta = scanner.nextLine();
    switch (tipoCuenta){
      case "1":
        tipoCuenta = MenuConstantes.CUENTA_CORRIENTE;
        break;
      case "2":
        tipoCuenta = MenuConstantes.CUENTA_AHORRO;
        break;
      case "3":
        tipoCuenta = MenuConstantes.CUENTA_VISTA;
        break;
      default:
        System.out.println(MenuConstantes.DATO_NOVALIDO);
        break;
    }

    System.out.println(MenuConstantes.MONTO_RETIRAR);
    monto = scanner.nextLong();

    // Buscar al cliente en el mapa totalClientes
    Cliente cliente = totalClientes.get(nombreTitular);
    if (cliente != null) {
      // Si el cliente existe, obtener la cuenta por tipo
      CuentaBancaria cuenta = cliente.obtenerCuentaPorTipo(tipoCuenta);
      if (cuenta != null) {
        // Si la cuenta existe, depositar el monto
        double nuevoSaldo = cuenta.retirar(monto);
        System.out.println("Saldo actual: $" + nuevoSaldo);

        // Formatear los datos de la transacción
        String informacionTransaccion = cuenta.formatearTransaccion("Retiro", monto, cliente.getNombre(), cliente.getRut());

        // Simular errores para guardar en el archivo
        SimularError.simularRandomError(cuenta, cliente);

        // Guardar los datos de la transacción en el archivo
        try {
          FileManager.escribirEnArchivo(informacionTransaccion);
        } catch (IOException e) {
          System.err.println(MenuConstantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
        }
      } else {
        System.out.println(MenuConstantes.CUENTA_NOEXISTE);
      }
    } else {
      // Si el cliente no existe, mostrar un mensaje de error
      System.out.println(MenuConstantes.CLIENTE_NOEXISTE);
    }
  }

  public void mostrarCuenta(Scanner scanner){
    System.out.println("[Perfil]\n");

    System.out.println(MenuConstantes.NOMBRE_CLIENTE);
    nombreTitular = scanner.nextLine();

    // Buscar al cliente en el diccionario
    Cliente cliente = totalClientes.get(nombreTitular);
    if (cliente != null) {
      // Si el cliente existe, mostrar su información
      cliente.mostrarInformacionCliente();
    } else {
      // Si el cliente no existe, mostrar un mensaje de error
      System.out.println(MenuConstantes.CLIENTE_NOEXISTE);
    }
  }

  public static void main(String[] args) {
    // instancia de la clase Banco
    Banco banco = new Banco();

    // scanner para la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    try {
      FileManager.abrirArchivo();
    } catch (IOException e) {
      System.err.println(MenuConstantes.ERROR_ESCRIBIR_ARCHIVO + e.getMessage());
      return;
    }

    // Clientes de ejemplo
    Cliente cliente1 = new Cliente("Juan Herrera", "30", "12345678-9", "Calle 123", "123456789", "juan@example.com");
    Cliente cliente2 = new Cliente("Maria Paredes", "25", "98765432-1", "Avenida 456", "987654321", "maria@example.com");

    // Agregar los clientes al mapa totalClientes
    banco.totalClientes.put(cliente1.getNombre(), cliente1);
    banco.totalClientes.put(cliente2.getNombre(), cliente2);

    // Cuentas de ejemplo
    cliente1.agregarCuenta(new CuentaCorriente());
    cliente1.agregarCuenta(new CuentaAhorro(50000));
    cliente2.agregarCuenta(new CuentaVista(20000));

    while (true) {
      //Menu
      System.out.println(MenuConstantes.NOMBRE_BANCO);
      System.out.println(MenuConstantes.MENU_PRINCIPAL);

      // opcion del usuario
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1:
          banco.crearCuenta(scanner);
          break;
        case 2:
          banco.realizarDeposito(scanner);
          break;
        case 3:
          banco.realizarRetiro(scanner);
          break;
        case 4:
          banco.mostrarCuenta(scanner);
          break;
        case 5:
          System.out.println(MenuConstantes.PROGRAMA_FINALIZADO);
          try {
            FileManager.cerrarArchivo();
          } catch (IOException e) {
            System.err.println("Error al cerrar el archivo: " + e.getMessage());
          }
          return;
        default:
          System.out.println(MenuConstantes.DATO_NOVALIDO);
          break;
      }
    }
  }
}
