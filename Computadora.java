public class Computadora implements DispositivoElectronico {
  
    String marca;
    boolean estado; //false = apagado ; true = encendido
    String tipo;


    public Computadora(String marca, boolean estado) {
        this.marca = marca;
        this.estado = estado;
        this.tipo = "Computadora";
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public void apagar() {
        estado = false;
    }

    @Override
    public void encender() {
        estado = true;        
    }

    @Override
    public boolean validacionEncendido() {
        if(estado){
            return estado;
        }else{
            return estado;
        }
    }

    public String toString() {
        return "Marca: " + marca;
    }

    
}
