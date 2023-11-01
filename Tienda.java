import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Tienda{
    public static void main(String[] args) throws IOException {
        int menu, opc;
        boolean continuar=true;
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
                    break;
                case 3:
                    dispositivos.get(0).getEstado();
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