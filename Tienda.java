import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tienda{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File archivo = new File("dispositivos.csv");
        ArrayList<DispositivoElectronico> dispositivos = new ArrayList<DispositivoElectronico>();
        menu();
    }


    private static void menu(){
        System.out.println("***** MENÚ TIENDA ELECTROTECH *****");
        System.out.println("1. Desplegar información de cada dispositivo");
        System.out.println("2. Validación dispositivos encendidos/apagados");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

    }

    
    private static void guardarDatos(/*ArrayList<Jugador> jugadores,*/File archivo){
        
                StringBuilder contenidoExistente = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        contenidoExistente.append(linea).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*
                for(int i=0; i<jugadores.size(); i++){
                    contenidoExistente.append(jugadores.get(i).toString()).append("\n");

                    try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
                        writer.print(contenidoExistente.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                */
    }

    private static void cargarArchivos(File archivo) throws IOException{
        try {
            Scanner escritor = new Scanner(archivo);
            if(archivo.exists()){
                System.out.println("Archivo " + archivo + " cargado correctamente!!");
            }else{        
                System.out.println( archivo + " no se cargó correctamente");
            }
            escritor.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Upsi");
            e.printStackTrace();
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