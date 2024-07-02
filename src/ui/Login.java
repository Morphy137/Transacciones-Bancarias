package ui;

import javax.swing.*;

public class Login extends JFrame{
  private JPanel panelLogin;
  private JTextField txtUser;
  private JPasswordField txtPass;
  private JButton bttonCancel;
  private JButton bttonConfirm;

  private static final Screen screen = new Screen();

  public Login(){
    initComponents();
    addListeners();
  }

  private void initComponents(){
    this.setTitle("CONTROL DE ACCESO");
    this.setContentPane(panelLogin);

    designComponents();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setSize(300, 200);
    this.setLocationRelativeTo(null);
  }

  private void designComponents(){
    //UI
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      // If Nimbus is not available, you can set the GUI to another look and feel.
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Windows".equals(info.getName())) {
          try {
            UIManager.setLookAndFeel(info.getClassName());
          } catch (Exception ex) {
            ex.printStackTrace();
          }
          break;
        }
      }
    }
  }

  private void addListeners(){
    bttonCancel.addActionListener(_ -> removeFields());
    bttonConfirm.addActionListener(_ -> checkLogin());
  }

  private void removeFields(){
    txtUser.setText("");
    txtPass.setText("");
  }

  private void checkLogin(){
    String user = txtUser.getText();
    String pass = new String(txtPass.getPassword());

    if(user.equals("admin") && pass.equals("admin")){
      JOptionPane.showMessageDialog(this, "Bienvenido " + user);
      this.setVisible(false); // Cerrar ventana de login
      screen.setVisible(true);
    }
  }
}
