package ui;

import banco.entidades.Cliente;
import banco.entidades.Cuenta;
import banco.transacciones.TransaccionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    buttonTransactions.addActionListener(e -> mostrarTransacciones());
    cbClientes.addActionListener(e -> mostrarCuentas());
  }

  private void addComboBoxClient(){
    cbClientes.removeAllItems();
    List<Cliente> clientes = transaccionManager.obtenerClientes();
    for (Cliente cliente : clientes) {
      cbClientes.addItem(cliente.getNombre());
    }
  }

  private void mostrarCuentas() {
    String clienteSeleccionado = (String) cbClientes.getSelectedItem();
    if (clienteSeleccionado == null) {
      return;
    }

    List<Cliente> clientes = transaccionManager.obtenerClientes();
    Cliente cliente = clientes.stream()
            .filter(c -> c.getNombre().equals(clienteSeleccionado))
            .findFirst()
            .orElse(null);

    if (cliente == null) {
      return;
    }

    DefaultListModel<String> modeloCliente = new DefaultListModel<>();
    modeloCliente.addElement(String.format("<html>Nombre: %s<br>RUT: %s</html>", cliente.getNombre(), cliente.getRut()));
    listCliente.setModel(modeloCliente);

    DefaultListModel<String> modeloCtaCte = new DefaultListModel<>();
    DefaultListModel<String> modeloCtaAhorro = new DefaultListModel<>();
    DefaultListModel<String> modeloCtaVista = new DefaultListModel<>();

    for (Cuenta cuenta : cliente.getCuentas().values()) {
      String tipoCuenta = cuenta.getTipo();
      String cuentaInfo = String.format("<html>Nro: %s<br>Saldo: %s</html>", cuenta.getNumero(), cuenta.getSaldo());
      switch (tipoCuenta) {
        case "Cta.Cte":
          modeloCtaCte.addElement(cuentaInfo);
          break;
        case "Cta.Ahorro":
          modeloCtaAhorro.addElement(cuentaInfo);
          break;
        case "Vista":
          modeloCtaVista.addElement(cuentaInfo);
          break;
      }
    }

    listCtaCte.setModel(modeloCtaCte);
    listCtaAhorro.setModel(modeloCtaAhorro);
    listCtaVista.setModel(modeloCtaVista);
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
    } else if(!checkCtaCorrienteSelected && !checkCtaAhorroSelected && !checkCtaVistaSelected) {
      JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un tipo de cuenta", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Obtener las transacciones del cliente seleccionado
    List<String[]> transacciones = transaccionManager.obtenerTransacciones().stream()
            .filter(transaccion -> transaccion[0].equals(clienteSeleccionado))
            // Filtrar transacciones por tipo (Giro o Deposito)
            .filter(transaccion ->
                    (checkGiroSelected && "Giro".equals(transaccion[4])) ||
                            (checkDepositoSelected && "Deposito".equals(transaccion[4]))
            )
            // Filtrar transacciones por tipo de cuenta (Cta.Cte, Cta.Ahorro, Vista)
            .filter(transaccion ->
                    (checkCtaCorrienteSelected && "Cta.Cte".equals(transaccion[2])) ||
                            (checkCtaAhorroSelected && "Cta.Ahorro".equals(transaccion[2])) ||
                            (checkCtaVistaSelected && "Vista".equals(transaccion[2]))
            )
            .sorted(Comparator.comparing(t -> t[6]))
            .collect(Collectors.toList());

    // Añadir las transacciones filtradas a la tabla
    /* inicio de bloque */
    for (String[] transaccion : transacciones) {
      model.addRow(new Object[]{
              transaccion[2], // Tipo Cuenta
              transaccion[3], // Nro. Cuenta
              transaccion[4], // Tipo Transacción
              transaccion[5], // Monto
              transaccion[6], // Costo
              transaccion[7], // Saldo
              transaccion[8]  // Fecha
      });
    }
    /* final de bloque */
  }

  public static Screen getInstance() {
    if (instance == null) {
      instance = new Screen();
    }
    return instance;
  }
}
