/**
 * Esta clase representa una computadora como un dispositivo electrónico.
 * Implementa la interfaz DispositivoElectronico para definir métodos comunes
 * a los dispositivos electrónicos, como encender, apagar y verificar su estado.
 */
public class Computadora implements DispositivoElectronico {

    // Atributos de la computadora
    String marca; // La marca de la computadora
    boolean estado; // El estado de la computadora (false = apagado; true = encendido)
    String tipo; // El tipo de dispositivo, en este caso, "Computadora"

    /**
     * Constructor de la clase Computadora.
     *
     * @param marca  La marca de la computadora.
     * @param estado El estado inicial de la computadora (true para encendida, false para apagada).
     */
    public Computadora(String marca, boolean estado) {
        this.marca = marca;
        this.estado = estado;
        this.tipo = "Computadora";
    }

    /**
     * Obtiene el tipo de dispositivo.
     *
     * @return El tipo de dispositivo, en este caso, "Computadora".
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la marca de la computadora.
     *
     * @return La marca de la computadora.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Obtiene el estado de la computadora.
     *
     * @return true si la computadora está encendida, false si está apagada.
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Apaga la computadora, estableciendo su estado a false.
     */
    @Override
    public void apagar() {
        estado = false;
    }

    /**
     * Enciende la computadora, estableciendo su estado a true.
     */
    @Override
    public void encender() {
        estado = true;
    }

    /**
     * Verifica el estado de la computadora y lo retorna.
     *
     * @return true si la computadora está encendida, false si está apagada.
     */
    @Override
    public boolean validacionEncendido() {
        return estado;
    }

    /**
     * Convierte la información de la computadora en una cadena de texto.
     *
     * @return Una cadena de texto que representa la computadora en el formato "tipo,marca,estado".
     */
    public String toString() {
        return tipo + "," + marca + "," + estado;
    }
}
