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
    public boolean validacionEncendido() {
        if(estado){
            return estado;
        }else{
            return estado;
        }
    }
    
}
