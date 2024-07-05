package banco.transacciones;

import banco.entidades.Cliente;
import banco.entidades.Cuenta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransaccionManager {
  private Map<String, Cliente> clientes;

  private static TransaccionManager instance;

  public TransaccionManager(){
    this.clientes = new HashMap<>();
  }

  public static TransaccionManager getInstance() {
    if (instance == null) {
      instance = new TransaccionManager();
    }
    return instance;
  }

  public void leerArchivo(String rutaArchivo){
    try(BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
      String linea;
      while((linea = br.readLine()) != null){
        procesarLinea(linea);
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  private void procesarLinea(String linea){
    String[] partes = linea.split("//");
    String tipoTransaccion = partes[0].trim();

    // Crear variables para almacenar los datos de la transacción
    String datosCliente = null;
    String datosCuenta = null;
    String datosMonto = null;
    String fecha = null;

    if(partes.length > 4){
      datosMonto = partes[1].trim();
      datosCuenta = partes[2].trim();
      datosCliente = partes[3].trim();
      fecha = partes[4].trim();
    }
    else{
      datosCuenta = partes[1].trim();
      datosCliente = partes[2].trim();
      fecha = partes[3].trim();
    }

    String[] datosClientesPartes = datosCliente.split(" ");
    String nombre = datosClientesPartes[0] + " " + datosClientesPartes[1];
    String rut = datosClientesPartes[2];

    // si el cliente no existe, se crea uno nuevo y se agrega a la lista de clientes
    Cliente cliente = clientes.computeIfAbsent(rut, k -> new Cliente(nombre, rut));

    if(tipoTransaccion.startsWith("Crear")){
      String[] datosCuentaPartes = datosCuenta.split(" ");

      String numeroCuenta = datosCuentaPartes[0];
      double saldoInicial = Double.parseDouble(datosCuentaPartes[1]);
      String tipoCuenta = tipoTransaccion.split(" ")[1]; //"Crear Cta.Cte" -> "Cta.Cte"
      Cuenta cuenta = new Cuenta(numeroCuenta, saldoInicial, tipoCuenta);

      // Agregar la cuenta al cliente correspondiente
      cliente.agregarCuenta(numeroCuenta, cuenta);
    }
    else{
      // Procesar las transacciones de giro y depósito
      String[] datosMontoPartes = datosMonto.split(" ");
      double costo = 0;

      String transaccion = tipoTransaccion.split(" ")[0]; // Tipo Transaccion
      double monto = Double.parseDouble(datosMontoPartes[0]); // Monto de transaccion
      if(datosMontoPartes.length > 1){
        costo = Double.parseDouble(datosMontoPartes[1]); // Costo de transaccion
      }
      String tipoCuenta = datosCuenta.split(" ")[0];
      String numeroCuenta = datosCuenta.split(" ")[1];
      double saldo = Double.parseDouble(datosCuenta.split(" ")[2]);

      Cuenta cuenta = cliente.getCuentas().get(numeroCuenta);

      if(cuenta == null){
        System.out.println("No se encontró la cuenta " + numeroCuenta + " para el cliente " + cliente.getNombre());
        return;
      }

      if(tipoTransaccion.equals("Giro")){
        cuenta.setSaldo(cuenta.getSaldo() - monto);
      }
      else if( tipoTransaccion.equals("Deposito")){
        cuenta.setSaldo(cuenta.getSaldo() + monto);
      }

      String transaccionFormateada = String.join("//",
          tipoCuenta,
          numeroCuenta,
          tipoTransaccion,
          String.valueOf(monto),
          String.valueOf(costo),
          String.valueOf(cuenta.getSaldo()),
          fecha.split("/")[0] // Solo la fecha sin la hora
      );

      cuenta.agregarTransaccion(transaccionFormateada);
    }
  }

  public List<String[]> obtenerTransacciones() {
    List<String[]> transacciones = new ArrayList<>();
    for (Cliente cliente : clientes.values()) {
      for (Cuenta cuenta : cliente.getCuentas().values()) {
        for (String transaccion : cuenta.getTransacciones()) {
          String[] detallesTransaccion = transaccion.split("//");
          transacciones.add(new String[]{cliente.getNombre(), cliente.getRut(), detallesTransaccion[0], detallesTransaccion[1], detallesTransaccion[2], detallesTransaccion[3], detallesTransaccion[4], detallesTransaccion[5], detallesTransaccion[6]});
        }
      }
    }
    return transacciones;
  }

  public List<Cliente> obtenerClientes() {
    return clientes.values().stream().collect(Collectors.toList());
  }
}
