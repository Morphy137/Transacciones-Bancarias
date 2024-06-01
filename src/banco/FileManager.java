package banco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager extends SimularError {
  private static BufferedWriter writer;

  private FileManager() {
    // Constructor privado para evitar que se creen instancias de la clase
  }

  public static void abrirArchivo() throws IOException {
    writer = new BufferedWriter(new FileWriter("registro_movimientos.txt", true));
  }

  public static void escribirEnArchivo(String informacion) throws IOException {
    writer.write(informacion + "\n");
  }

  public static void cerrarArchivo() throws IOException {
    writer.close();
  }
}
