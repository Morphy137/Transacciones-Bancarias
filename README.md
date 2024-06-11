Durante el desarrollo de la “Entrega I”, simulamos la generación de transacciones de una entidad 
bancaria con errores de transmisión. Recordemos que las transacciones generadas 
correspondían a los siguientes tipos: apertura de cuentas (de diferente tipo), depósitos y giros de 
dinero, y transferencias de fondos entre cuentas. Lo que implicó el diseño de diferentes clases 
para representar diferentes entidades involucradas en el sistema bancario.
El problema que se simulaba correspondía a la recepción – con errores – de manera centralizada 
de las transacciones generadas en diferentes puntos. A modo de recordatorio, en el ejemplo que 
se muestra en la Figura 2, se observa que se ha registrado un giro (primera línea) antes que la 
creación de la cuenta asociada (segunda línea). Más en detalle, la primera línea indica una 
transacción en que se realiza un giro de $1.100 con un recargo de $120 sobre la cuenta vista 
1234-12, a la que luego le queda un saldo de $8.780 y que pertenece a Jorge Soto. Luego se indica 
rut del usuario, fecha y hora de la transacción y, finalmente, la fecha y hora de registro. La 
siguiente línea corresponde al registro de la creación de una “cuenta vista”, la que posee el 
número 1234-12 y un saldo inicial de $10.000.- y que fue abierta por Jorge Soto. El resto de la 
línea indica fecha y hora de la transacción y, finalmente, la fecha y hora de registro. Finalmente, 
la última línea corresponde a un depósito de $1.220.- a la cuenta vista número 1234-12, cuyo 
nuevo saldo es $10.000.- luego se muestra información del titular de la cuenta, fecha y hora de 
la transacción y de registro.
