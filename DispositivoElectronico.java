/**
 * Esta interfaz define el contrato para los dispositivos electrónicos,
 * especificando los métodos comunes que deben ser implementados por las clases
 * que representan dispositivos electrónicos, como computadoras, teléfonos, etc.
 */
public abstract interface DispositivoElectronico {

    /**
     * Enciende el dispositivo electrónico.
     */
    public abstract void encender();

    /**
     * Apaga el dispositivo electrónico.
     */
    public abstract void apagar();

    /**
     * Obtiene el tipo del dispositivo electrónico.
     *
     * @return El tipo del dispositivo, como una cadena de texto.
     */
    public abstract String getTipo();

    /**
     * Obtiene el estado actual del dispositivo electrónico.
     *
     * @return true si el dispositivo está encendido, false si está apagado.
     */
    public abstract boolean getEstado();

    /**
     * Verifica si el dispositivo electrónico está encendido.
     *
     * @return true si el dispositivo está encendido, false si está apagado.
     */
    public abstract boolean validacionEncendido();
}
