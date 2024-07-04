package ui;

import banco.entidades.Cliente;
import banco.transacciones.TransaccionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Screen extends BaseWindow {
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

  private TransaccionManager transaccionManager = TransaccionManager.getInstance();

  private static Screen instance;

  private Screen() {
    transaccionManager = new TransaccionManager();
    transaccionManager.leerArchivo("src/banco/archivos/transacciones.txt");

    designComponents();
    initComponents();
    addListeners();
    addComboBoxClient();
  }

  private void initComponents() {
    this.setTitle("BANCO");
    this.setContentPane(panelScreen);
    this.setUndecorated(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.pack();
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);

    tableTransacciones.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Tipo Cuenta", "Nro. Cuenta", "Tipo Transacción", "Monto", "Costo", "Saldo", "Fecha"}));
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

  private void addComboBoxClient(){
    cbClientes.removeAllItems();
    List<Cliente> clientes = transaccionManager.obtenerClientes();
    for (Cliente cliente : clientes) {
      cbClientes.addItem(cliente.getNombre());
    }
  }

  public void mostrarTransacciones() {
    DefaultTableModel model = (DefaultTableModel) tableTransacciones.getModel();
    model.setRowCount(0);

    String clienteSeleccionado = (String) cbClientes.getSelectedItem();
    if(clienteSeleccionado == null) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    boolean checkGiroSelected = checkGiro.isSelected();
    boolean checkDepositoSelected = checkDeposito.isSelected();

    boolean checkCtaCorrienteSelected = checkCtaCorriente.isSelected();
    boolean checkCtaAhorroSelected = checkCtaAhorro.isSelected();
    boolean checkCtaVistaSelected = checkCtaVista.isSelected();

    if (!checkGiroSelected && !checkDepositoSelected) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un tipo de transacción", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    else if(!checkCtaCorrienteSelected && !checkCtaAhorroSelected && !checkCtaVistaSelected) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un tipo de cuenta", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Obtener las transacciones del cliente seleccionado
    List<String[]> transacciones = transaccionManager.obtenerTransacciones().stream()
        .filter(transaccion -> {
          String nombre = transaccion[0];
          String rut = transaccion[1];
          String tipoCuenta = transaccion[2];
          String tipoTransaccion = transaccion[4];

          // Filtrar por cliente seleccionado
          if (!clienteSeleccionado.equals(nombre)) {
            return false;
          }

          // Filtrar por tipo de transacción
          if (checkGiroSelected && tipoTransaccion.equals("Giro")) {
            return true;
          }
          if (checkDepositoSelected && tipoTransaccion.equals("Deposito")) {
            return true;
          }

          // Filtrar por tipo de cuenta
          if (checkCtaCorrienteSelected && tipoCuenta.equals("Cta.Cte")) {
            return true;
          }
          if (checkCtaAhorroSelected && tipoCuenta.equals("Cta.Ahorro")) {
            return true;
          }
          if (checkCtaVistaSelected && tipoCuenta.equals("Vista")) {
            return true;
          }

          return false;
        })
        .sorted(Comparator.comparing(t -> t[6])) // Ordenar por fecha
        .collect(Collectors.toList());

    // Añadir las transacciones filtradas a la tabla
    for (String[] transaccion : transacciones) {
      model.addRow(new Object[]{transaccion[2], transaccion[3], transaccion[4], transaccion[5], transaccion[6], transaccion[7], transaccion[8]});
    }
  }

  public static Screen getInstance() {
    if (instance == null) {
      instance = new Screen();
    }
    return instance;
  }
}