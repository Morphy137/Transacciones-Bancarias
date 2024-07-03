package banco.transacciones;

import banco.entidades.Cliente;
import banco.entidades.Cuenta;
import banco.entidades.Transaccion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
  private static BufferedWriter writer;
  private static BufferedReader reader;

  // Ruta donde se guardan las transacciones, debería modificarse dependiendo de donde se ejecute el compilado
  // Si es en terminar debe ser:"../src/banco/archivos/", desde el IDE:"src/banco/archivos/"
  private static final String RUTEARCHIVE = "src/banco/archivos/";

  private FileManager() {
    // Constructor privado para evitar que se creen instancias de la clase
  }

  // Métodos para abrir, escribir y cerrar el archivo de registro de movimientos
  public static void abrirArchivo(String archivoEntrada) throws IOException {
    writer = new BufferedWriter(new FileWriter(RUTEARCHIVE + archivoEntrada + ".txt", true));
  }

  public static void escribirEnArchivo(String data) throws IOException {
    writer.write(data + "\n");
  }

  public static void cerrarArchivo() throws IOException {
    writer.close();
  }

  // Método para leer el archivo de transacciones y retornar una lista con las transacciones
  public static List<String> leerArchivo(String archivoEntrada) throws IOException {
    reader = new BufferedReader(new FileReader(RUTEARCHIVE + archivoEntrada + ".txt"));
    List<String> transacciones = new ArrayList<>();
    String linea;

    while ((linea = reader.readLine()) != null) {
      transacciones.add(linea);
    }

    reader.close();
    return transacciones;
  }

  public static void guardarTransaccionVerificada(List<String> verifiedTransaction, String archivoSalida) throws IOException {
    writer = new BufferedWriter(new FileWriter(RUTEARCHIVE + archivoSalida + ".txt", true));

    for (String transaction : verifiedTransaction) {
      writer.write(transaction + "\n");
    }

    writer.close();
  }

  public static List<Cliente> leerClientesDesdeArchivo(String archivoEntrada) throws IOException {
    reader = new BufferedReader(new FileReader(RUTEARCHIVE + archivoEntrada + ".txt"));
    List<Cliente> clientes = new ArrayList<>();
    String linea;
    Cliente clienteActual = null;

    while ((linea = reader.readLine()) != null) {
      // Parsear la línea para crear instancias de Cliente, Cuenta y Transaccion
      String[] partes = linea.split("\\|\\|");
      if (partes.length < 6) continue;

      String tipoTransaccion = partes[0].trim();
      double monto = Double.parseDouble(partes[1].trim().replace(",", "."));
      String tipoCuentaNumero = partes[2].trim();
      String nombreCliente = partes[3].trim();
      String rutCliente = partes[4].trim();
      String fechaHora = partes[5].trim();

      // Crear o buscar el cliente actual
      if (clienteActual == null || !clienteActual.getRut().equals(rutCliente)) {
        clienteActual = new Cliente("", "", nombreCliente, "", rutCliente);
        clientes.add(clienteActual);
      }

      // Crear la cuenta y transacción
      String tipoCuenta = tipoCuentaNumero.split(" ")[0];
      String numeroCuenta = tipoCuentaNumero.split(" ")[1];
      Cuenta cuenta = new Cuenta(numeroCuenta, monto, tipoCuenta);
      Transaccion transaccion = new Transaccion(tipoTransaccion, monto, LocalDateTime.parse(fechaHora), nombreCliente);

      cuenta.agregarTransaccion(transaccion);
      clienteActual.agregarCuenta(cuenta);
    }

    reader.close();
    return clientes;
  }
}