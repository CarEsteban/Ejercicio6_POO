/**
 * Esta clase representa un teléfono como un dispositivo electrónico.
 * Implementa la interfaz DispositivoElectronico para definir métodos comunes
 * a los dispositivos electrónicos, como encender, apagar y verificar su estado.
 */
public class Telefono implements DispositivoElectronico {

    // Atributos del teléfono
    String modelo; // El modelo del teléfono
    boolean estado; // El estado del teléfono (false = apagado; true = encendido)
    String tipo; // El tipo de dispositivo, en este caso, "Telefono"

    /**
     * Constructor de la clase Telefono.
     *
     * @param modelo El modelo del teléfono.
     * @param estado El estado inicial del teléfono (true para encendido, false para apagado).
     */
    public Telefono(String modelo, boolean estado) {
        this.modelo = modelo;
        this.estado = estado;
        this.tipo = "Telefono";
    }

    /**
     * Obtiene el tipo de dispositivo.
     *
     * @return El tipo de dispositivo, en este caso, "Telefono".
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el modelo del teléfono.
     *
     * @return El modelo del teléfono.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Obtiene el estado del teléfono.
     *
     * @return true si el teléfono está encendido, false si está apagado.
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Apaga el teléfono, estableciendo su estado a false.
     */
    @Override
    public void apagar() {
        estado = false;
    }

    /**
     * Enciende el teléfono, estableciendo su estado a true.
     */
    @Override
    public void encender() {
        estado = true;
    }

    /**
     * Verifica el estado del teléfono y lo retorna.
     *
     * @return true si el teléfono está encendido, false si está apagado.
     */
    @Override
    public boolean validacionEncendido() {
        return estado;
    }

    /**
     * Convierte la información del teléfono en una cadena de texto.
     *
     * @return Una cadena de texto que representa el teléfono en el formato "tipo,modelo,estado".
     */
    public String toString() {
        return tipo + "," + modelo + "," + estado;
    }
}
