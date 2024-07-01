package ui;

import javax.swing.*;

public class Screen extends JFrame{
  private JPanel panelScreen;
  private JComboBox<String> cbClientes;
  private JCheckBox checkBox4;
  private JCheckBox checkBox5;
  private JButton bttonTransactions;
  private JTable table1;
  private JCheckBox checkCtaCorriente;
  private JCheckBox checkCtaAhorro;
  private JCheckBox checkCtaVista;
  private JTable table2;

  public Screen() {
    initComponents();
    addListeners();
  }

  private void initComponents() {
    this.setTitle("BANCO");
    this.setContentPane(panelScreen);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
  }

  private void addListeners() {
    bttonTransactions.addActionListener(_ -> mostrarTransacciones());
  }

  public void mostrarTransacciones() {
    // Mostrar transacciones del cliente seleccionado
  }
}
