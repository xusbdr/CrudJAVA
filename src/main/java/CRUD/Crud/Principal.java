package CRUD.Crud;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import CRUD.Crud.*;



public class Principal {
	
	
	 private static OperacionesAlumnos operacionesAlumnos;
	    private static OperacionesNotas operacionesNotas;

	    
	    public static void main(String[] args) {
	        operacionesAlumnos = new OperacionesAlumnos();
	        operacionesNotas = new OperacionesNotas();

	        Scanner scanner = new Scanner(System.in);
	        boolean exit = false;

	        // Ciclo principal
	        while (!exit) {
	            // Mostrar menú inicial
	            System.out.println("Seleccione la operación a realizar :");
	            System.out.println("1. Alumnos");
	            System.out.println("2. Notas");
	            System.out.println("3. Salir");

	            // Leer opción del usuario
	            int option = readOption(scanner);

	            // Ejecutar la opción seleccionada
	            switch (option) {
	                case 1:
	                    menuAlumnos();
	                    break;
	                case 2:
	                    menuNotas();
	                    break;
	                case 3:
	                    exit = true; // Salir del ciclo y finalizar la aplicación
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	                    break;
	            }
	        }

	        // Cerrar el scanner al finalizar
	        scanner.close();
	    }
	    
	    
	    
	    // --------------------
	    
	   
	    
	    // --------------------------- ALUMNOS -----------------------
	    
	    
	    
	    private static void menuAlumnos() {
	    	
	        Scanner teclado = new Scanner(System.in);
	        boolean exit = false;

	        while (!exit) {
	           
	            System.out.println("Seleccione una opción para Alumnos:");
	            System.out.println("1. Crear registro");
	            System.out.println("2. Recuperar registro");
	            System.out.println("3. Actualizar y modificar registro");
	            System.out.println("4. Eliminar registro");
	            System.out.println("5. Volver al menú principal");
	           
	           
	            int option = readOption(teclado);

	            
	            switch (option) {
	                case 1:
	                	
	                    System.out.println("");
	                    
	                    
	                	System.out.println("Ingrese el DNI del Nuevo alumno:");
	                    int dni = teclado.nextInt();
	                	
	                	System.out.println("Ingrese el nombre del Nuevo alumno:");
	                    String nombre = teclado.next();
	                   
	                    System.out.println("Ingrese los apellidos del Nuevo alumno:");	                    
	                    String apellidos = teclado.next();
	                    
	                    System.out.println("Ingrese el teléfono del Nuevo alumno:");
	                    int telefono = teclado.nextInt();
	                    
	                    operacionesAlumnos.insertarAlumno(dni, nombre, apellidos, telefono);
	                    
	                    break;
	                    
	                  
	                case 2:
	                	 System.out.println("Ingrese el DNI del alumno para mostrar sus notas:");
		                    int dniMostrar = teclado.nextInt();
		                    operacionesAlumnos.recuperarAlumno(dniMostrar);
	                    break;
	                    
	                case 3:
	                	System.out.println("Ingrese el DNI del alumno a actualizar:");
	                    int dniActualizar = teclado.nextInt();
	                    operacionesAlumnos.actualizarAlumno(dniActualizar);
	                
	                    // Mostrar mensaje de confirmación
	                    System.out.println("Alumno actualizado correctamente.");
	                    // Limpiar el buffer del scanner
	                    teclado.nextLine();
	                    
	                    break;
	                    
	                case 4:
	                	 System.out.println("Ingrese el DNI del alumno a borrar:");
	                	 int BORRAR = teclado.nextInt();
		                    operacionesAlumnos.borrarAlumno(BORRAR);		               
	                    break;
	                    
	                case 5:
	                	System.out.println("");
	                    exit = false; // Salir del menú de operaciones para alumnos
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	                    teclado.nextLine(); // Consumir la nueva línea pendiente en el buffer del scanner
	                    break;
	            }
	            
	        }

	        teclado.close();
	    }
	    
	    
	    
	    // ---------
	    

	  
	// -------------------- NOTAS --------------------------------------    
	    
	    
	    
	    
	    // Menú para operaciones sobre la tabla de notas
	    private static void menuNotas() {
	    	
	        Scanner teclado= new Scanner(System.in);
	        
	        boolean exit = false;

	        while (!exit) {
	            // Mostrar menú de operaciones para notas
	            System.out.println("Seleccione una opción para Notas:");
	            System.out.println("1. Crear o modificar registro Notas");
	            System.out.println("2. Mostrar registro Notas");	           
	            System.out.println("3. Salir registro Notas");
	          

	            // Leer opción del usuario
	            System.out.print("Opción: ");
	            int option = teclado.nextInt();
	           
	            
	            
	            // Ejecutar la opción seleccionada
	            switch (option) {
	                case 1:
	                	// Solicitar al usuario que ingrese el DNI del alumno para actualizar notas
	                    System.out.println("Ingrese el DNI del alumno para actualizar notas:");
	                    Scanner scannerDNI = new Scanner(System.in); // Creamos un nuevo Scanner para leer el DNI
	                    
	                    int dniActualizar = 0; // Inicializamos el DNI como 0
	                    boolean validDNI = false; // Bandera para verificar si el DNI es válido

	                    while (!validDNI) {
	                        try {
	                            String input = scannerDNI.nextLine(); // Leer la entrada como una cadena
	                            if (!input.isEmpty()) { // Verificar si la entrada no está vacía
	                                dniActualizar = Integer.parseInt(input); // Convertir la entrada a entero
	                                
	                                // Comprobar si el DNI es un número positivo
	                                if (dniActualizar > 0) {
	                                    validDNI = true; // El DNI es válido, salir del bucle
	                                   
	                                } else {
	                                    System.out.println("Error: Por favor, ingrese un número de DNI válido.");
	                                }
	                            } else {
	                                System.out.println("Error: Por favor, ingrese un número de DNI válido.");
	                            }
	                        } catch (NumberFormatException e) {
	                            System.out.println("Error: Por favor, ingrese un número de DNI válido.");
	                        }
	                    }
	                    
	                    // Ahora que tenemos un DNI válido, procedemos con la actualización de las notas
	                    operacionesNotas.actualizarNota(dniActualizar);
	                    scannerDNI.close(); // Cerrar el scanner para liberar recursos
	                    teclado.nextLine(); // Consumir la nueva línea pendiente
	                	
	                    break;
	                    
	                    
	                case 2:
	                	// Solicitar al usuario que ingrese el DNI del alumno para mostrar sus notas
	                    System.out.println("Ingrese el DNI del alumno para mostrar sus notas:");
	                    int dniMostrar = teclado.nextInt();
	                    operacionesNotas.recuperarNota(dniMostrar);
	                    break;
	                    
	                    
	                case 3:
	                	exit = true; // Salir del menú de operaciones para notas
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	                  break;
	                 
	            }
	        }

	        teclado.close();
	    }
	    
	    
	    
	    
	    // ---------------------
	    
	    
	    
	    
	    // ----------------------------------
	    

	    
	    
	    
	    
	    // Método para leer una opción del usuario y manejar posibles excepciones
	    
	    private static int readOption(Scanner scanner) {
	       
	    	int option = 0; // Inicializa la opción como 0 (valor inválido)
	        boolean validOption = false; // Variable para verificar si la opción es válida

	        // Solicitar al usuario que ingrese una opción hasta que proporcione una opción válida
	        while (!validOption) {
	            try {
	                System.out.print("Opción: ");

	                // Leer la entrada como un entero
	                option = scanner.nextInt();

	                validOption = true; // La opción es válida si no se lanza una excepción
	            } catch (Exception e) {
	                // Si se lanza una excepción, simplemente continuar leyendo la próxima entrada
	                scanner.nextLine(); // Limpiar el buffer del scanner
	            }
	        }
	        return option;
	    }
 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}



//