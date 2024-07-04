package banco.transacciones;

import banco.entidades.Cliente;
import banco.entidades.Cuenta;
import banco.entidades.CuentaCorriente;
import banco.entidades.Transaccion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

  // Ruta donde se guardan las transacciones, debería modificarse dependiendo de donde se ejecute el compilado
  // Si es en terminal debe ser:"../src/banco/archivos/", desde el IDE:"src/banco/archivos/"
  private static final String RUTEARCHIVE = "src/banco/archivos/";

  private FileManager() {
    // Constructor privado para evitar que se creen instancias de la clase
  }

  public static List<Cliente> leerClientesDesdeArchivo(String archivoEntrada) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(RUTEARCHIVE + archivoEntrada));
    List<Cliente> clientes = new ArrayList<>();
    String linea;
    Cliente clienteActual = null;

    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss/dd-MM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");

    while ((linea = reader.readLine()) != null) {
      // Parsear la línea para crear instancias de Cliente, Cuenta y Transaccion
      String[] partes = linea.split("\\|\\|");
      if (partes.length < 6) continue;

      String tipoTransaccion = partes[0].trim();
      double monto = Double.parseDouble(partes[1].trim().replace(",", "."));
      String nombreCliente = partes[3].trim();
      String rutCliente = partes[4].trim();
      String fechaHora = partes[5].trim();

      // Creacion del cliente actual
      if (clienteActual == null || !clienteActual.getRut().equals(rutCliente)) {
        clienteActual = new Cliente(nombreCliente, rutCliente);
        clientes.add(clienteActual);
      }

      // Creacion de la cuenta y transacción
        Cuenta cuenta = new CuentaCorriente();

      // Analisis de la fecha y hora correctaa

      LocalDateTime dateTime;
      try {
        dateTime = LocalDateTime.parse(fechaHora, formatter1);
      } catch (DateTimeParseException e1) {
        try {
          dateTime = LocalDateTime.parse(fechaHora, formatter2);
        } catch (DateTimeParseException e2) {
          System.err.println("Error parsing date: " + fechaHora);
          continue; // Saltar esta transacción si hay un error de análisis
        }
      }

//      Transaccion transaccion = new Transaccion(tipoTransaccion, monto, dateTime, nombreCliente);

//      cuenta.agregarTransaccion(transaccion);
//      clienteActual.agregarCuenta(cuenta);
    }

    reader.close();
    return clientes;
  }
}