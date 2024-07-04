package ui;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends BaseWindow {
  private JPanel panelLogin;
  private JTextField txtUser;
  private JPasswordField txtPass;
  private JButton btnCancel;
  private JButton btnConfirm;
  private JLabel labelExit;

  private String userData;
  private String passData;

  public Login() {
    designComponents();
    initComponents();
    addListeners();

  }

  private void initComponents() {
    this.setTitle("CONTROL DE ACCESO");
    this.setContentPane(panelLogin);
    this.setResizable(false);
    this.setUndecorated(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setSize(300, 200);
    this.setLocationRelativeTo(null);
  }

  private void designComponents() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  private void addListeners() {
    super.addCloseFunctionality(labelExit);
    btnCancel.addActionListener(_ -> removeFields());
    btnConfirm.addActionListener(_ -> checkLogin());
    txtUser.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        if(txtUser.getText().equals("  Ingrese su usuario")){
          txtUser.setText("");
        }
      }
      @Override
      public void focusLost(FocusEvent e) {
        if(txtUser.getText().equals("")){
          txtUser.setText("  Ingrese su usuario");
        }
      }
    });
    txtPass.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        String passText = new String(txtPass.getPassword());
        if(passText.equals("123456")){
          txtPass.setText("");
        }
      }
      @Override
      public void focusLost(FocusEvent e) {
        if(txtPass.getPassword().length == 0){
          txtPass.setText("123456");
        }
      }
    });
  }

  private void removeFields() {
    txtUser.setText("");
    txtPass.setText("");
  }

  private void checkLogin(){
    leerCredenciales();

    String user = txtUser.getText();
    String pass = new String(txtPass.getPassword());

    if(user.equals(userData) && pass.equals(passData)){
      JOptionPane.showMessageDialog(this, "¡Bienvenido " + user + "!");
      this.setVisible(false); // Ocultar ventana de login

      Screen screen = Screen.getInstance();
      screen.setVisible(true);

      this.dispose(); // Liberar recursos de la ventana de login
    }
    else {
      JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void leerCredenciales() {
    try (BufferedReader br = new BufferedReader(new FileReader("src/banco/archivos/userPass.txt"))) {
      String linea = br.readLine();
      if (linea != null) {
        String[] partes = linea.split(" ");
        userData = partes[0];
        passData = partes[1];
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
