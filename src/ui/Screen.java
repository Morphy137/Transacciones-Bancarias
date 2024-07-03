package ui;

import banco.entidades.Cliente;
import banco.entidades.Cuenta;
import banco.entidades.Transaccion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Screen extends JFrame{
  private JPanel panelScreen;
  private JComboBox<String> cbClientes;
  private JCheckBox checkGiro;
  private JCheckBox checkDeposito;
  private JButton bttonTransactions;
  private JCheckBox checkCtaCorriente;
  private JCheckBox checkCtaAhorro;
  private JCheckBox checkCtaVista;
  private JTable tableTransacciones;
  private JList listCliente;
  private JList listCtaCte;
  private JList listCtaAhorro;
  private JList listCtaVista;
  private JPanel panelCliente;
  private JPanel panelCuentasYTransacciones;
  private JPanel panelCuentas;
  private JPanel panelTransacciones;
  private JLabel seleccionarCuenta;
  private JLabel seleccionarTransacciones;
  private JPanel panelListas;
  private JPanel panelListasSeleccionadas;
  private JLabel enunciadoListasSeleccionadas;
  private List<Cliente> clientes;


  public Screen(List<Cliente> clientes) {
    this.clientes = clientes;
    initComponents();
    addListeners();
    populateClientes();
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

  private void populateClientes() {
    for (Cliente cliente : clientes) {
      cbClientes.addItem(cliente.getNombre());
    }
  }

  public void mostrarTransacciones() {
    // Mostrar transacciones del cliente seleccionado
    String clienteSeleccionado = (String) cbClientes.getSelectedItem();
    for (Cliente cliente : clientes) {
      if (cliente.getNombre().equals(clienteSeleccionado)) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tipo");
        model.addColumn("Monto");
        model.addColumn("Fecha");
        model.addColumn("Cliente");

        for (Cuenta cuenta : cliente.getCuentas()) {
          for (Transaccion transaccion : cuenta.getTransacciones()) {
            model.addRow(new Object[]{
                    transaccion.getTipo(),
                    transaccion.getMonto(),
                    transaccion.getFechaTransaccion(),
                    transaccion.getCliente()
            });
          }
        }

        tableTransacciones.setModel(model);
        break;
      }
    }
  }
}
