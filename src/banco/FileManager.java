package banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager extends SimularError {
  private static BufferedWriter writer;
  private static BufferedReader reader;

  private static String filePath = "src/banco/registro_movimientos.txt";

  private FileManager() {
    // Constructor privado para evitar que se creen instancias de la clase
  }

  // Metodos para abrir, escribir y cerrar el archivo de registro de movimientos
  public static void abrirArchivo() throws IOException {
    writer = new BufferedWriter(new FileWriter(filePath, true));
  }

  public static void escribirEnArchivo(String informacion) throws IOException {
    writer.write(informacion + "\n");
  }

  public static void cerrarArchivo() throws IOException {
    writer.close();
  }

  // Metodo para leer el archivo de transacciones y retornar una lista con las transacciones
  public static List<String> leerArchivo() throws IOException {
    reader = new BufferedReader(new FileReader(filePath));
    List<String> transacciones = new ArrayList<>();
    String linea;

    while ((linea = reader.readLine()) != null) {
      transacciones.add(linea);
    }

    reader.close();
    return transacciones;
  }

  public static void escribirTransaccionesVerificadas(List<String> transaccionesVerificadas) throws IOException {
    writer = new BufferedWriter(new FileWriter("transacciones_verificadas.txt", true));

    for (String transaccion : transaccionesVerificadas) {
      writer.write(transaccion + "\n");
    }

    writer.close();
  }
}
