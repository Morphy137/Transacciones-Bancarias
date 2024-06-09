package banco.transacciones;

import banco.Cliente;

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

  public static void escribirCliente(Cliente cliente) throws IOException {
    writer = new BufferedWriter(new FileWriter("clientes.txt", true));
    String clienteFormateado = cliente.formatearCliente();

    writer.write(clienteFormateado);
    writer.newLine();
    writer.close();
  }

  public static List<Cliente> leerClientes() throws IOException {
    reader = new BufferedReader(new FileReader("clientes.txt"));
    List<Cliente> clientes = new ArrayList<>();
    String linea;

    while ((linea = reader.readLine()) != null) {
      // Dividir la línea en sus componentes
      String[] partes = linea.split(" \\|\\| ");
      // Crear un nuevo objeto Cliente
      Cliente cliente = new Cliente(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
      // Agregar el cliente a la lista de clientes
      clientes.add(cliente);
    }

    reader.close();
    return clientes;
  }
}
