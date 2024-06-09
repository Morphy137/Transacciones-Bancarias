package banco.transacciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
  private static BufferedWriter writer;
  private static BufferedReader reader;

  private static String ruteArchive = "src/banco/archivos/";

  private FileManager() {
    // Constructor privado para evitar que se creen instancias de la clase
  }

  // Metodos para abrir, escribir y cerrar el archivo de registro de movimientos
  public static void abrirArchivo(String filePath) throws IOException {
    writer = new BufferedWriter(new FileWriter(ruteArchive + filePath, true));
  }

  public static void escribirEnArchivo(String data) throws IOException {
    writer.write(data + "\n");
  }

  public static void cerrarArchivo() throws IOException {
    writer.close();
  }

  // Metodo para leer el archivo de transacciones y retornar una lista con las transacciones
  public static List<String> leerArchivo(String filePath) throws IOException {
    reader = new BufferedReader(new FileReader(ruteArchive + filePath));
    List<String> transacciones = new ArrayList<>();
    String linea;

    while ((linea = reader.readLine()) != null) {
      transacciones.add(linea);
    }

    reader.close();
    return transacciones;
  }

  public static void writeVerifiedTransactions(List<String> verifiedTransaction, String filePath) throws IOException {
    writer = new BufferedWriter(new FileWriter(filePath, true));

    for (String transaction : verifiedTransaction) {
      writer.write(transaction + "\n");
    }

    writer.close();
  }
}
