public class Telefono implements DispositivoElectronico {
    
    String modelo;
    boolean estado; //false = apagado ; true = encendido
    String tipo;


    public Telefono(String modelo,boolean estado) {
        this.modelo = modelo;
        this.estado = estado;
        this.tipo = "Telefono";
    }

    public String getTipo() {
        return tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean getEstado(){
        return estado;
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
        return "Modelo: " + modelo;
    }

    
}
