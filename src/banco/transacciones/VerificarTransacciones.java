package banco.transacciones;

import banco.MenuConstantes;

import java.util.ArrayList;
import java.util.List;

public class VerificarTransacciones {
  public List<String> verificarTransacciones(List<String> transacciones) {
    List<String> transaccionesVerificadas = new ArrayList<>();

    for (String transaccion : transacciones) {
      // Dividir la transaccion en sus partes
      String[] partes = transaccion.split("||");

      // Verificar que la transaccion tenga el formato correcto
      if(partes.length != 6) {
        continue;
      }

      // Trim() para eliminar espacios en blanco
      String tipoTransaccion = partes[0].trim();
      double monto = Double.parseDouble(partes[1].trim().replace(",", "."));
      String tipoCuentaNumero = partes[2].trim();
      String nombreCliente = partes[3].trim();
      String rutCliente = partes[4].trim();
      String fechaHora = partes[5].trim();

      // Verificar que el número de cuenta tenga 9 digitos
      String[] cuentaPartes = tipoCuentaNumero.split(" ");
      if(cuentaPartes.length != 2 || cuentaPartes[1].length() != 9) {
        // si no tiene 9 digitos, rellenar con ceros
        while(cuentaPartes[1].length() < 9) {
          cuentaPartes[1] = "0" + cuentaPartes[1];
        }
        tipoCuentaNumero = cuentaPartes[0] + " " + cuentaPartes[1];
      }

      // Verificar nombre del cliente
      if(!nombreCliente.contains(" ")) {
        nombreCliente += " Apellido"; // Agregar apellido si no tiene
      }

      // Verificar tipo de transaccion
      boolean transaccionValida = true;
      switch (tipoTransaccion){
        case MenuConstantes.CREACION_CUENTA:
          // No debe tener saldo inicial negativo
          if(monto < 0) {
            monto = Math.abs(monto);
          }
          break;
        case MenuConstantes.DEPOSITO:
          // No debe tener monto negativo
          if(monto < 0) {
            monto = Math.abs(monto);
          }
          break;
      }

      // Corregir el formato de la fecha y hora (fecha antes que hora)
      String[] fechaHoraPartes = fechaHora.split("/");
      if (fechaHoraPartes.length == 2) {
        String parte1 = fechaHoraPartes[0].trim();
        String parte2 = fechaHoraPartes[1].trim();

        // Verificar si la primera parte es la hora (contiene ':')
        if (parte1.contains(":")) {
          // Intercambiar fecha y hora
          fechaHora = parte2 + "/" + parte1;
        } else {
          fechaHora = parte1 + "/" + parte2;
        }
      }

      // Crear la transacción corregida
      String transaccionCorregida = String.join("||",
              tipoTransaccion,
              String.format("%.2f", monto).replace(".", ","),
              tipoCuentaNumero,
              nombreCliente,
              rutCliente,
              fechaHora
      );

      transaccionesVerificadas.add(transaccionCorregida);
    }



    return transaccionesVerificadas;
  }
}
