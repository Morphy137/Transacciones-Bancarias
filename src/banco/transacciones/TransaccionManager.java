package banco.transacciones;

import banco.entidades.Transaccion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransaccionManager {
  private static final String RUTA_ARCHIVO = "src/banco/archivos/";

  public static List<Transaccion> leerTransaccionesDesdeArchivo(String archivoEntrada) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader (RUTA_ARCHIVO + archivoEntrada));
    List<Transaccion> transacciones = new ArrayList<>();
    String linea;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");

    while((linea = reader.readLine()) != null){
      String[] partes = linea.split("\\|\\|");
      if(partes.length < 4) continue; //se asegura que la linea contenga todas las partes para continuar

      /*
      * partes[0] = tipo
      * partes[1] = nroCuenta monto, separados por espacio
      * partes[2] = cliente rut, separados por espacio
      * partes[3] = fechaTransaccion y hora
      */

      String tipo = partes[0].trim();
      String[] cuentaMonto = partes[1].split(" ");
      String nroCuenta = cuentaMonto[0].trim();
      double monto = Double.parseDouble(cuentaMonto[1].trim());
      String[] clienteRut = partes[2].split(" ");
      String cliente = clienteRut[0].trim();
      String rut = clienteRut[1].trim();
      LocalDateTime fechaTransaccion = LocalDateTime.parse(partes[3].trim(), formatter);

      Transaccion transaccion = new Transaccion(tipo, nroCuenta, monto, cliente, rut, fechaTransaccion);
      transacciones.add(transaccion);
    }

    reader.close();
    return transacciones;
  }
}
