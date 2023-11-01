import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Tienda{
    public static void main(String[] args) throws IOException {
        int menu, opc;
        boolean continuar=true,continuarSubMenu=true;
        Scanner scanner = new Scanner(System.in);
        File archivo = new File("dispositivos.csv");
        ArrayList<DispositivoElectronico> dispositivos = new ArrayList<DispositivoElectronico>();
        cargarArchivos(archivo,dispositivos);  




        while(continuar){
            menu();
            opc = scanner.nextInt();
            scanner.nextLine();

            switch(opc){
                case 1:
                    System.out.println("INFORMACIÓN DE CADA DISPOSITIVO");
                    imprimirDispositivos(dispositivos, "Telefono");
                    imprimirDispositivos(dispositivos, "Computadora");


                    break;
                case 2:
                    System.out.println("VALIDACION DISPOSITIVOS ENCENDIDOS O APAGADOS");
                    mostrarDispositivosPorEstado(dispositivos, true);
                    mostrarDispositivosPorEstado(dispositivos, false);
                    break;
                case 3:
                
                    while(continuarSubMenu){
                        System.out.println("Desea cambiar el estado de uno existente (1) o agregar uno (2)?");
                        menu = scanner.nextInt();
                        scanner.nextLine();

                        switch (menu) {
                            case 1:
                                String cambiar;
                                boolean estadoActual=false;
                                System.out.println("Ingrese el modelo o marca exacto para cambiar el estado");
                                imprimirDispositivos(dispositivos, "Telefono");
                                imprimirDispositivos(dispositivos, "Computadora");
                                cambiar = scanner.nextLine();
                                for (DispositivoElectronico dispositivo : dispositivos) {
                                    if(dispositivo instanceof Telefono && cambiar.equals(((Telefono)dispositivo).getModelo())){
                                        System.out.println("Estado actual: " + dispositivo.getEstado());
                                        if(dispositivo.getEstado()){
                                            dispositivo.apagar();
                                        }else{
                                            dispositivo.encender();
                                        }
                                        System.out.println("Estado cambiado: " + dispositivo.getEstado());

                                    }else if(dispositivo instanceof Computadora && cambiar.equals(((Computadora)dispositivo).getMarca())){    
                                        System.out.println("Estado actual: " + dispositivo.getEstado());
                                        if(dispositivo.getEstado()){
                                            dispositivo.apagar();
                                            
                                            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                                                String line;
                                                br.readLine();

                                                while ((line = br.readLine()) != null) {
                                                    String[] campos = line.split(",");
                                                    if (campos[1].equals(cambiar)) {
                                                        String estadoString = estadoActual ? "Encendido" : "Apagado";

                                                        campos[3] = estadoString;
                                                    }


                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }


                                        }else{
                                            dispositivo.encender();
                                        }
                                        System.out.println("Estado cambiado: " + dispositivo.getEstado());

                                    }
                                }


                                continuarSubMenu = volverAlMenu(scanner, " a cambiar algo? ");
                                break;
                        
                            case 2:
                                


                                continuarSubMenu = volverAlMenu(scanner, " a cambiar algo? ");
                                break;
                        
                            default:
                                System.out.println("Esta opcion no existe");
                                continuarSubMenu=false;
                                break;
                        }

                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    continuar=false;
                    break;
                default:
                    break;

            }

            continuar = volverAlMenu(scanner, " al menú? ");
        }
    }


    private static void menu(){
        System.out.println("***** MENÚ TIENDA ELECTROTECH *****");
        System.out.println("1. Desplegar información de cada dispositivo");
        System.out.println("2. Validación dispositivos encendidos/apagados");
        System.out.println("3. Ingresar nuevo dispositivo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

    }

    private static void cargarArchivos(File archivo, ArrayList<DispositivoElectronico> dispositivos) throws IOException{
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            boolean estado = false;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos[0].equals("Telefono")) {

                    String modelo = campos[1].trim();
                    if(campos[3].equals("Encendido")){
                        estado = true;
                    }else if (campos[3].equals("Apagado")) {
                        estado = false;
                    }
                    
                    dispositivos.add(new Telefono(modelo, estado));

                }else if(campos[0].equals("Computadora")){
                    
                    String marca = campos[2].trim();
                    if(campos[3].equals("Encendido")){
                        estado = true;
                    }else if (campos[3].equals("Apagado")) {
                        estado = false;
                    }
                    dispositivos.add(new Computadora(marca, estado));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirDispositivos(ArrayList<DispositivoElectronico> dispositivos, String tipo) {
        int contadorDispositivo = 0;
        for (DispositivoElectronico dispositivo : dispositivos) {
            if (dispositivo.getTipo().equals(tipo)) {
                contadorDispositivo++;
            }
        }
        System.out.println(tipo + ":" + "("+contadorDispositivo+")");
        for (DispositivoElectronico dispositivo : dispositivos) {
            if (dispositivo.getTipo().equals(tipo)) {
                System.out.println("\t"+dispositivo);
            }
        }
    }
    
    private static void mostrarDispositivosPorEstado(ArrayList<DispositivoElectronico> dispositivos, boolean estado) {
        String estadoString = estado ? "Encendidos" : "Apagados";
        System.out.println("Dispositivos " + estadoString + ":");
    
        for (DispositivoElectronico dispositivo : dispositivos) {
            if (dispositivo instanceof Telefono && estado == ((Telefono) dispositivo).getEstado()) {
                System.out.println("\t"+dispositivo.getTipo()+" - "+dispositivo);
            } else if (dispositivo instanceof Computadora && estado == ((Computadora) dispositivo).getEstado()) {
                System.out.println("\t"+dispositivo.getTipo()+" - "+dispositivo);
            }
        }
    }
    

    private static boolean volverAlMenu(Scanner scanner, String eleccion) {
        System.out.println("¿Desea volver" + eleccion + " (1: Sí, 0: No): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) {
            if (eleccion.equals(" al menú? ")) {
                System.out.println("Saliendo del programa.");
                return false;
            } else {
                System.out.println("Saliendo de la opción.");
                return false;
            }
        } else {
            return true;
        }
    }
}