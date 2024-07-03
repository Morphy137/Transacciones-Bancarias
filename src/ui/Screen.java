package ui;

import banco.entidades.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Screen extends BaseWindow{
  //panel
  private JPanel panelScreen;
  //checkbox
  private JCheckBox checkGiro;
  private JCheckBox checkDeposito;
  private JCheckBox checkCtaCorriente;
  private JCheckBox checkCtaAhorro;
  private JCheckBox checkCtaVista;
  //list
  private JList<String> listCliente;
  private JList<String> listCtaCte;
  private JList<String> listCtaAhorro;
  private JList<String> listCtaVista;
  //others
  private JLabel labelExit;
  private JTable tableTransacciones;
  private JButton buttonTransactions;
  private JComboBox<String> cbClientes;

  private List<Cliente> clientes;
  private static Screen instance;

  private Screen(List<Cliente> clientes) {
    this.clientes = clientes;
    designComponents();
    initComponents();
    addListeners();
    populateClientes();
  }

  private void initComponents() {
    this.setTitle("BANCO");
    this.setContentPane(panelScreen);
    this.setUndecorated(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.pack();
    this.setSize(800, 600);
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
    buttonTransactions.addActionListener(_ -> mostrarTransacciones());
  }

  private void populateClientes() {
    cbClientes.removeAllItems();
    for (Cliente cliente : clientes) {
      cbClientes.addItem(cliente.getNombre());
    }
  }

  public void mostrarTransacciones() {
    String clienteSeleccionado = (String) cbClientes.getSelectedItem();

    boolean checkGiroSelected = checkGiro.isSelected();
    boolean checkDepositoSelected = checkDeposito.isSelected();
    boolean checkCtaCorrienteSelected = checkCtaCorriente.isSelected();
    boolean checkCtaAhorroSelected = checkCtaAhorro.isSelected();
    boolean checkCtaVistaSelected = checkCtaVista.isSelected();

    if (!checkGiroSelected && !checkDepositoSelected && !checkCtaCorrienteSelected && !checkCtaAhorroSelected && !checkCtaVistaSelected) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un tipo de transacción", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    DefaultTableModel model = (DefaultTableModel) tableTransacciones.getModel();
    model.setRowCount(0);

    /*
    *
    * Aquí se debe implementar la lógica para iterar el cliente seleccionado
    * y mostrar las transacciones que tiene asociadas en la tabla, pero antes
    * verificar que la transaccion a mostrar sea la que pide en los checkboxes
    * simplemente tiene que tener por ejemplo "checkGiroSelected && transaccion.getTipo().equals("Giro")"
    * o algo similar para no complicarse tanto.
    * La tabla tiene que tener 7 columnas: Tipo Cuenta, Nro. Cuenta, Tipo Transaccion, Monto, Costo, Saldo, Fecha
    * por ende donde pide Monto (actual) se obtiene al momento de guardar estos valores que se sacan del archivo de texto
    * (lo pide el enunciado)
    * y se guardan en las clases correspondientes o variables.
    * Finalmente, mostrar las transacciones de manera ordenada por las fechas
    */

  }

  public static Screen getInstance(List<Cliente> clientes) {
    if (instance == null) {
      instance = new Screen(clientes);
    }
    return instance;
  }
}