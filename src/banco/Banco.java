package banco;

import banco.entidades.Cliente;
import banco.transacciones.FileManager;
import ui.Login;

import java.io.IOException;
import java.util.List;

public class Banco {
  private List<Cliente> clientes;

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
      clientes = FileManager.leerClientesDesdeArchivo("transacciones");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
