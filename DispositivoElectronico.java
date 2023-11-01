public abstract interface DispositivoElectronico {
    public abstract void encender();
    public abstract void apagar();
    public abstract String getTipo();
    public abstract boolean getEstado();
    public abstract boolean validacionEncendido();
}
