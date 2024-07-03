package banco;

import banco.entidades.Cliente;
import banco.transacciones.FileManager;
import ui.Login;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Banco {
  private List<Cliente> clientes;
  private static final Logger logger = Logger.getLogger(Banco.class.getName());

  public Banco() {
    // Constructor
  }

  public static void main(String[] args) {
    Banco banco = new Banco();
    banco.cargarClientes();

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
}