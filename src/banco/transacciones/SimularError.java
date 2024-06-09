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
          // Fija la edad en cero
          cuenta.setNumeroCuenta("0");
          break;
        case 1:
          // Establece el saldo en negativo
          cuenta.setSaldo(-1);
          break;
        case 2:
          // Elimina el nombre del cliente
          cliente.setNombre(null);
          break;
      }
    }
  }
}
