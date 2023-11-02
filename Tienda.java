import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Tienda representa una aplicación de gestión de dispositivos electrónicos.
 * Permite al usuario realizar diversas operaciones relacionadas con dispositivos, como mostrar información,
 * validar su estado, cambiar el estado y agregar nuevos dispositivos.
 */
public class Tienda {
    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     * @throws IOException Si ocurre un error de entrada/salida al trabajar con archivos.
     */
    public static void main(String[] args) throws IOException {
        // Declaración de variables y objetos utilizados en la aplicación.
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
                                boolean estadoActual = false;
                                System.out.println("Ingrese el modelo o marca exacto para cambiar el estado");
                                imprimirDispositivos(dispositivos, "Telefono");
                                imprimirDispositivos(dispositivos, "Computadora");
                                cambiar = scanner.nextLine();
                                boolean cambioRealizado = false;

                                for (DispositivoElectronico dispositivo : dispositivos) {
                                    if (dispositivo instanceof Telefono && cambiar.equals(((Telefono) dispositivo).getModelo())) {
                                        estadoActual = dispositivo.getEstado();
                                        System.out.println("Estado actual: " + estadoActual);

                                        if (estadoActual) {
                                            dispositivo.apagar();
                                        } else {
                                            dispositivo.encender();
                                        }

                                        System.out.println("Estado cambiado: " + dispositivo.getEstado());
                                        cambioRealizado = true;
                                    } else if (dispositivo instanceof Computadora && cambiar.equals(((Computadora) dispositivo).getMarca())) {
                                        estadoActual = dispositivo.getEstado();
                                        System.out.println("Estado actual: " + estadoActual);

                                        if (estadoActual) {
                                            dispositivo.apagar();
                                        } else {
                                            dispositivo.encender();
                                        }

                                        System.out.println("Estado cambiado: " + dispositivo.getEstado());
                                        cambioRealizado = true;
                                    }
                                }

                                if (cambioRealizado) {
                                    guardarDatos(dispositivos, archivo); // Actualiza el archivo CSV después del cambio
                                }

                                continuarSubMenu = volverAlMenu(scanner, " a cambiar algo? ",archivo,dispositivos);
                                break;
                        
                            case 2:
                                
                            System.out.print("Por favor, introduce el tipo: ");
                            String tipo = scanner.nextLine();

                            if(tipo.equals("Telefono")){

                                System.out.print("Por favor, introduce el modelo: ");
                                String modelo = scanner.nextLine();

                                dispositivos.add(new Telefono(modelo, false));
                        
                            }else if(tipo.equals("Computadora")){
                                    
                                System.out.print("Por favor, introduce la marca: ");
                                String marca = scanner.nextLine();
                                dispositivos.add(new Telefono(marca, false));
                        
                            }


                                continuarSubMenu = volverAlMenu(scanner, " a cambiar algo? ", archivo, dispositivos);
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
                    guardarDatos(dispositivos, archivo);
                    break;
                default:
                    break;

            }

            continuar = volverAlMenu(scanner, " al menú? ", archivo, dispositivos);
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

    private static void guardarDatos(ArrayList<DispositivoElectronico> dispositivos, File archivo) {
        StringBuilder contenidoExistente = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoExistente.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (DispositivoElectronico dispositivo : dispositivos) {
            if (!contenidoExistente.toString().contains(dispositivo.toString())) {
                contenidoExistente.append(dispositivo).append("\n");
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.print(contenidoExistente.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    
                    estado = Boolean.parseBoolean(campos[2]);
                    
                    dispositivos.add(new Telefono(modelo, estado));

                }else if(campos[0].equals("Computadora")){
                    
                    String marca = campos[1].trim();

                    estado = Boolean.parseBoolean(campos[2]);

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
    

    private static boolean volverAlMenu(Scanner scanner, String eleccion, File archivo, ArrayList<DispositivoElectronico> dispositivos) {
        System.out.println("¿Desea volver" + eleccion + " (1: Sí, 0: No): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) {
            if (eleccion.equals(" al menú? ")) {
                System.out.println("Saliendo del programa.");
                guardarDatos(dispositivos, archivo);

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