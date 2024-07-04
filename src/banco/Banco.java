package banco;

import ui.Login;

public class Banco {
  public static void main(String[] args) {
    // Instancia de la clase Login
    Login login = new Login();
    login.setVisible(true);

    System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));
  }
}