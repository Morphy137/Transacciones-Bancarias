package banco.entidades;

import java.time.LocalDateTime;

public record Transaccion(String tipo, double monto, LocalDateTime fechaTransaccion, String cliente) {

}
