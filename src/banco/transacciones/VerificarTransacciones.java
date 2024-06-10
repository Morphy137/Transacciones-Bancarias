package banco.transacciones;

import banco.MenuConstantes;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

public class VerificarTransacciones {
  public List<String> verificarTransacciones(List<String> transacciones) {
    List<String> transaccionesVerificadas = new ArrayList<>();

    for (String transaccion : transacciones) {
      // Dividir la transaccion en sus partes
      String[] partes = transaccion.split("\\|\\|");

      // Verificar que la transaccion tenga el formato correcto
      if(partes.length != 6) {
        continue;
      }

      // Trim() para eliminar espacios en blanco al final y al inicio
      String tipoTransaccion = partes[0].trim();
      double monto = Double.parseDouble(partes[1].trim().replace(",", "."));
      String tipoCuentaNumero = partes[2].trim();
      String nombreCliente = partes[3].trim();
      String rutCliente = partes[4].trim();
      String fechaHora = partes[5].trim();

      // Separa el tipo de cuenta y el número de cuenta
      String[] cuentaPartes = tipoCuentaNumero.split(" ");
      if(cuentaPartes.length > 1) {
        // si no tiene 9 dígitos, rellenar con ceros
        cuentaPartes[1] = cuentaPartes[1].replaceAll("\\D", ""); // Elimina cualquier carácter que no sea un dígito
        while(cuentaPartes[1].length() < 9) {
          cuentaPartes[1] = "0" + cuentaPartes[1];
        }
        tipoCuentaNumero = cuentaPartes[0] + " " + cuentaPartes[1];
      }

      // Verificar nombre del cliente
      if(!nombreCliente.contains(" ")) {
        nombreCliente += " " + nombreCliente; // Repetimos el nombre para simular un apellido
      }

      // Verificar tipo de transacción
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

    // Ordenar las transacciones.txt por fecha y hora
    Collections.sort(transaccionesVerificadas, new Comparator<String>() {
      @Override
      public int compare(String t1, String t2) {
        // Extraer la fecha y hora de cada transacción
        String fechaHora1 = t1.split("\\|\\|")[5].trim();
        String fechaHora2 = t2.split("\\|\\|")[5].trim();

        // Eliminar cualquier carácter no deseado
        fechaHora1 = fechaHora1.replaceAll("[^\\d-/:]", "");
        fechaHora2 = fechaHora2.replaceAll("[^\\d-/:]", "");

        // Convertir las fechas y horas a objetos LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(fechaHora1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(fechaHora2, formatter);

        // Comparar los objetos LocalDateTime
        return dateTime1.compareTo(dateTime2);
      }
    });

    return transaccionesVerificadas;
  }
}
