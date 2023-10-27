public class Computadora implements DispositivoElectronico {
  
    String marca;
    boolean estado; //false = apagado ; true = encendido


    public Computadora(String marca, boolean estado) {
        this.marca = marca;
        this.estado = estado;
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
    public String validacionEncendido() {
        if(estado){
            return "El dispositivo está encendido";
        }else{
            return" El dispositivo está apagado";
        }
    }
    
}
