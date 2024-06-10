package banco.transacciones;

import banco.Cliente;
import banco.cuentas.CuentaBancaria;

import java.util.Random;

public class SimularError {

  private static final Random random = new Random();

  public static void simularRandomError(CuentaBancaria cuenta, Cliente cliente) {
    // Genera un número aleatorio entre 0 y 9
    int randomNumber = random.nextInt(10);

    // Si el número aleatorio es 0 (lo que tiene una probabilidad del 40%), genera un error
    if (randomNumber <= 4) {
      // Elige un número aleatorio y después se elige un tipo de error
      int errorType = random.nextInt(3);
      switch (errorType) {
        case 0:
          // Invierte el orden de la fecha y la hora
          String fechaHora = cuenta.getFechaHora();
          String[] fechaHoraPartes = fechaHora.split("/");

          cuenta.setFechaHora(fechaHoraPartes[1] + "/" + fechaHoraPartes[0]);
          break;
        case 1:
          // Establece el saldo en negativo
          cuenta.setSaldo(cuenta.getSaldo() * -1);
          break;
        case 2:
          // Genera un carácter aleatorio entre 'a' y 'z'
          char randomChar = (char) ('a' + random.nextInt(26));

          // Genera una posición aleatoria para insertar el carácter
          int randomPosition = random.nextInt(cuenta.getNumeroCuenta().length());

          // Inserta el carácter aleatorio en la posición aleatoria
          String numeroCuenta = cuenta.getNumeroCuenta();
          numeroCuenta = numeroCuenta.substring(0, randomPosition) + randomChar + numeroCuenta.substring(randomPosition);

          cuenta.setNumeroCuenta(numeroCuenta);
          break;
      }
    }
  }
}
