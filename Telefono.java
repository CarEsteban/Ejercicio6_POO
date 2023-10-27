public class Telefono implements DispositivoElectronico {
    
    String modelo;
    boolean estado; //false = apagado ; true = encendido


    public Telefono(String modelo) {
        this.modelo = modelo;
        this.estado = false;
    }

    public String getModelo() {
        return modelo;
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
