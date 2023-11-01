import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

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

                    imprimirDispositivos(dispositivos, "Telefono",null);
                    imprimirDispositivos(dispositivos, "Computadora",null);


                    break;
                case 2:
                    break;
                case 3:
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

    
private static void guardarDatos(ArrayList<DispositivoElectronico> dispositivos,File archivo){
        
                StringBuilder contenidoExistente = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        contenidoExistente.append(linea).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                
                for(int i=0; i<dispositivos.size(); i++){
                    contenidoExistente.append(dispositivos.get(i).toString()).append("\n");

                    try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
                        writer.print(contenidoExistente.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
    }

    private static void cargarArchivos(File archivo, ArrayList<DispositivoElectronico> dispositivos) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos[0].equals("Telefono")) {

                    String modelo = campos[1].trim();
                    boolean estado = Boolean.parseBoolean(campos[2]);

                    dispositivos.add(new Telefono(modelo, estado));

                }else if(campos[0].equals("Computadora")){
                    String marca = campos[2].trim();
                    boolean estado = Boolean.parseBoolean(campos[2]);
                    dispositivos.add(new Computadora(marca, estado));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirDispositivos(ArrayList<DispositivoElectronico> dispositivos, String tipo, String estadoS) {
        if(estadoS.equals("true")){
            boolean estado = Boolean.parseBoolean(estadoS);
            System.out.println(estado + ":");
            for (DispositivoElectronico dispositivo : dispositivos) {
                if (dispositivo.getTipo().equals(tipo)) {
                    System.out.println("\t"+dispositivo);
                }
            }
        }else if(estadoS.equals("false")){
            boolean estado = Boolean.parseBoolean(estadoS);

            System.out.println(estado + ":");
            for (DispositivoElectronico dispositivo : dispositivos) {
                if (dispositivo.getTipo().equals(tipo)) {
                    System.out.println("\t"+dispositivo);
                }
            }
        }else{
            System.out.println(tipo + ":");
            for (DispositivoElectronico dispositivo : dispositivos) {
                if (dispositivo.getTipo().equals(tipo)) {
                    System.out.println("\t"+dispositivo);
                }
            }
        }
        
        System.out.println(tipo + ":");
        for (DispositivoElectronico dispositivo : dispositivos) {
            if (dispositivo.getTipo().equals(tipo)) {
                System.out.println("\t"+dispositivo);
            }
        }
    }

    
    private static void validacionDispositivos(ArrayList<DispositivoElectronico> dispositivos, String tipo) {
        System.out.println(tipo + ":");
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