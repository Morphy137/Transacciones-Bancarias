package banco;

import banco.entidades.Cliente;
import banco.entidades.Transaccion;
import banco.transacciones.FileManager;
import banco.transacciones.TransaccionManager;
import ui.Login;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Banco {
  private List<Cliente> clientes;
  private List<Transaccion> transacciones;
  private static final Logger logger = Logger.getLogger(Banco.class.getName());

  public Banco() {
    // Constructor
    cargarTransacciones();
  }

  public static void main(String[] args) {
    Banco banco = new Banco();
    banco.cargarClientes();
    banco.cargarTransacciones();

    // Instancia de la clase Login
    Login login = new Login(banco.clientes);
    login.setVisible(true);
  }

  private void cargarClientes() {
    try {
      clientes = FileManager.leerClientesDesdeArchivo("transacciones_verificado.txt");
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error al cargar clientes desde el archivo", e);
    }
  }

  private void cargarTransacciones() {
    try {
      transacciones = TransaccionManager.leerTransaccionesDesdeArchivo("transacciones.txt");
      // Ahora las transacciones están cargadas y listas para ser usadas
    } catch (IOException e) {
      e.printStackTrace();
      // Manejar la excepción adecuadamente
    }
  }
}