/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raikiri.aplicacionlenguajes;

/**
 *
 * @author lutwi
 */
import java.util.*;
import java.io.*;

public class aplicacion {

    private List<Character> alfabeto;
    private List<String> cadenasAceptadas;
    private List<String> cadenasRechazadas;

    // Constructor para inicializar las listas
    public aplicacion() {
        alfabeto = new ArrayList<>();
        cadenasAceptadas = new ArrayList<>();
        cadenasRechazadas = new ArrayList<>();
    }

    // Método para definir el alfabeto
    public void definirAlfabeto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los caracteres del alfabeto separados por espacios:");
        String entrada = scanner.nextLine();
        for (char c : entrada.toCharArray()) {
            if (c != ' ' && !alfabeto.contains(c)) { // Ignorar espacios y caracteres duplicados
                alfabeto.add(c);
            }
        }
        if (alfabeto.isEmpty()) {
            System.out.println("No se ingresó un alfabeto válido.");
        } else {
            System.out.println("Alfabeto registrado: " + alfabeto);
        }
    }

    // Método para registrar cadenas y verificar si pertenecen al alfabeto
    public void registrarCadena() {
        if (alfabeto.isEmpty()) {
            System.out.println("Primero debe definir un alfabeto.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una cadena para registrar:");
        String cadena = scanner.nextLine();

        if (cadenaPerteneceAlfabeto(cadena)) {
            cadenasAceptadas.add(cadena);
            System.out.println("La cadena ha sido aceptada.");
        } else {
            cadenasRechazadas.add(cadena);
            System.out.println("La cadena no pertenece al alfabeto.");
        }
    }

    // Método que verifica si una cadena pertenece al alfabeto
    private boolean cadenaPerteneceAlfabeto(String cadena) {
        for (char c : cadena.toCharArray()) {
            if (!alfabeto.contains(c)) { // Si un caracter no está en el alfabeto, la cadena no es válida
                return false;
            }
        }
        return true;
    }

    // Método para generar un archivo TXT con los resultados
    public void exportarTXT() {
        if (alfabeto.isEmpty()) {
            System.out.println("No hay alfabeto definido para exportar.");
            return;
        }

        try {
            FileWriter writer = new FileWriter("alfabeto_y_cadenas.txt");
            writer.write("Alfabeto: " + alfabeto + "\n\n");
            writer.write("Cadenas aceptadas:\n");
            for (String cadena : cadenasAceptadas) {
                writer.write(cadena + "\n");
            }
            writer.write("\nCadenas rechazadas:\n");
            for (String cadena : cadenasRechazadas) {
                writer.write(cadena + "\n");
            }
            writer.close();
            System.out.println("Datos exportados exitosamente a 'alfabeto_y_cadenas.txt'.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al exportar los datos.");
        }
    }

    // Método principal para la ejecución del programa
    public static void main(String[] args) {
        aplicacion app = new aplicacion();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Bucle para mostrar el menú hasta que el usuario elija salir
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Definir alfabeto");
            System.out.println("2. Registrar cadena");
            System.out.println("3. Exportar a TXT");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                // Manejo del menú
                switch (opcion) {
                    case 1:
                        app.definirAlfabeto();
                        break;
                    case 2:
                        app.registrarCadena();
                        break;
                    case 3:
                        app.exportarTXT();
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                opcion = 0; // Para evitar que se salga del bucle en caso de error
            }
        } while (opcion != 4); // Continuar hasta que el usuario elija salir
    }
}
