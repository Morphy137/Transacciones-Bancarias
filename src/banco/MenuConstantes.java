package banco;

public class MenuConstantes {
  //Menu
  public static final String NOMBRE_BANCO = "[Banco Manitas]";
  public static final String MENU_PRINCIPAL = "\n1. Crear cuenta\n2. Depositar\n3. Retirar\n4. Cuenta\n5. Salir\n";
  public static final String MENU_CREAR_CUENTA = "\n1. Cuenta corriente\n2. Cuenta ahorro\n3. Cuenta vista\n";

  //Ingresar datos
  public static final String TIPO_CUENTA = "\nIngrese el tipo de cuenta";
  public static final String MONTO_DEPOSITO = "\nIngrese el monto a depositar";
  public static final String MONTO_RETIRAR = "\nIngrese el monto a retirar";

  //Ingresar datos Cliente
  public static final String NOMBRE_CLIENTE = "\nIngrese el nombre del cliente: ";
  public static final String EDAD_CLIENTE = "\nIngrese su edad: ";
  public static final String RUT_CLIENTE = "\nIngrese su RUT: ";
  public static final String DIRECCION_CLIENTE = "\nIngrese su direccion: ";
  public static final String TELEFONO_CLIENTE = "\nIngrese su numero de telefono: ";
  public static final String EMAIL_CLIENTE = "\nIngrese su email de contacto: ";

  //Exito
  public static final String RETIRO_EXITOSO = "Retiro realizado con éxito.";
  public static final String PROGRAMA_FINALIZADO = "Programa finalizado.";

  //Errores
  public static final String CUENTA_NOEXISTE = "La cuenta no existe";
  public static final String CLIENTE_NOEXISTE = "El cliente no existe";
  public static final String DATO_NOVALIDO = "Error en datos ingresados";
  public static final String ERROR_ESCRIBIR_ARCHIVO = "Error al escribir el archivo: ";
  public static final String SALDO_INSUFICIENTE = "Saldo insuficiente para realizar el retiro.";


  //Tipo de cuentas
  public static final String CUENTA_AHORRO = "Ahorro";
  public static final String CUENTA_CORRIENTE = "Corriente";
  public static final String CUENTA_VISTA = "Vista";

  //Fecha y hora
  public static final String FECHA_HORA = "Fecha y Hora: ";

  // constructor privado
  private MenuConstantes() {
  }
}
