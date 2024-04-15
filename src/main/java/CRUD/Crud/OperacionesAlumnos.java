package CRUD.Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;




public class OperacionesAlumnos {
	
	
	 private SessionFactory sessionFactory;
	 


	  public OperacionesAlumnos() {
	        // Inicializar la fábrica de sesiones de Hibernate
	        sessionFactory = new Configuration().configure().buildSessionFactory();
	    }

	 
	  
	  
	  
	  // ------------
	  
	 
	  public void insertarAlumno(int dni, String nombre, String apellidos, int telefono) {
		    
		  Session session = sessionFactory.openSession();

		    try {
		        Transaction transaction = session.beginTransaction();

		        Alumnos20 alumno = new Alumnos20();
		        alumno.setDni(dni);
		        alumno.setNombre(nombre);
		        alumno.setApellidos(apellidos);
		        alumno.setTelefono(telefono);

		        session.save(alumno);

		        // Insertar un registro en la tabla de notas con el mismo DNI y nombre
		        Notas notas = new Notas();
		        notas.setDni(dni);
		      
		        session.save(notas);

		        transaction.commit();
		        System.out.println("Alumno agregado correctamente.");
		        System.out.println("");
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        session.close();
		    }
		  
		}
	  
	  
	  
	  
	  // ------------------
	  
	  
	  

	    public Alumnos20 recuperarAlumno(int dni) {
	        
	    	Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        Alumnos20 alumno = null;

	        try {
	            transaction = session.beginTransaction();
	            alumno = session.createQuery("FROM Alumnos20 WHERE dni = :dni", Alumnos20.class)
	                            .setParameter("dni", dni)
	                            .getSingleResult();
	            transaction.commit();
	            if (alumno != null) {
	                System.out.println("");
	                System.out.println("Alumno encontrado:");
	                System.out.println("DNI: " + alumno.getDni());
	                System.out.println("Nombre: " + alumno.getNombre());
	                System.out.println("Apellidos: " + alumno.getApellidos());
	                System.out.println("Teléfono: " + alumno.getTelefono());
	                System.out.println("");
	            } else {
	                System.out.println("No se encontró ningún alumno con ese DNI.");
	            }
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return alumno;
	    }

	    
	    // -------------------------------
	    
	    
	   
	    
	    public void actualizarAlumno(int dni) {
	       
	    	 Session session = sessionFactory.openSession();
	    	    Transaction transaction = null;
	    	    Scanner scanner = new Scanner(System.in);

	    	    try {
	    	        transaction = session.beginTransaction();
	    	        Alumnos20 alumno = session.get(Alumnos20.class, dni);
	    	        if (alumno != null) {
	    	            System.out.println("Ingrese el nuevo nombre del alumno:");
	    	            String nombre = scanner.next();
	    	            
	    	            System.out.println("Ingrese los nuevos apellidos del alumno:");
	    	            String apellidos = scanner.next();
	    	            
	    	            System.out.println("Ingrese el nuevo teléfono del alumno:");                
	    	            int telefono = scanner.nextInt();
	    	            
	    	            // Consume el carácter de nueva línea en el búfer
	    	            scanner.nextLine();
	    	            
	    	            alumno.setNombre(nombre);
	    	            alumno.setApellidos(apellidos);
	    	            alumno.setTelefono(telefono);
	    	            session.update(alumno);
	    	            transaction.commit();
	    	            System.out.println("ALUMNO ACTUALIZADO CORRECTAMENTE .");
	    	            System.out.println("");
	    	           
	    	        } else {
	    	            System.out.println("No se encontró ningún alumno con ese DNI.");
	    	            System.out.println("");
	    	        }
	    	    } catch (Exception e) {
	    	        if (transaction != null) {
	    	            transaction.rollback();
	    	        }
	    	        e.printStackTrace();
	    	    } finally {
	    	        session.close();
	    	        scanner.close();
	    	    }
	    }
	    

	    
	    
	    // -----------------------
	    
	    
	  
	    public void borrarAlumno(int dni) {
	    	Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            
	            // Eliminar las notas asociadas al alumno con el DNI especificado
	            Query query = session.createQuery("DELETE FROM Notas n WHERE n.dni = :dni");
	            query.setParameter("dni", dni);
	            query.executeUpdate();
	            
	            // Ahora eliminar al alumno
	            Alumnos20 alumno = session.get(Alumnos20.class, dni);
	            if (alumno != null) {
	                session.delete(alumno);
	                transaction.commit();
	                System.out.println("Alumno y notas borradas correctamente.");
	                System.out.println("");
	            } else {
	                System.out.println("No se encontró ningún alumno con ese DNI.");
	                System.out.println("");
	            }
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }
	    
	    
	    // ----------------------------------------------------------
	    
	    
	   /*
	   
	     
	     
	     
	     
	// Método para listar todos los alumnos
	  
	   devuelva una lista de todos los alumnos registrados, 
	   mostrando sus detalles como DNI, nombre, apellidos y teléfono.
	
    public List<Alumnos20> listarAlumnos() {
        List<Alumnos20> alumnos = null;
        Session session = sessionFactory.openSession();
        try {
            alumnos = session.createQuery("FROM Alumnos20", Alumnos20.class).getResultList();
            System.out.println("Lista de alumnos:");
            for (Alumnos20 alumno : alumnos) {
                System.out.println("DNI: " + alumno.getDni() +
                                   ", Nombre: " + alumno.getNombre() +
                                   ", Apellidos: " + alumno.getApellidos() +
                                   ", Teléfono: " + alumno.getTelefono());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return alumnos;
    }
    
    
    
    
    
    
    
    

    // Método para calcular el promedio de notas de un alumno específico
    public double calcularPromedioNotas(int dni) {
        double promedio = 0.0;
        Session session = sessionFactory.openSession();
        try {
            List<Double> notas = session.createQuery("SELECT AVG(nota) FROM Notas WHERE dni = :dni", Double.class)
                                        .setParameter("dni", dni)
                                        .getResultList();
            if (notas.size() > 0 && notas.get(0) != null) {
                promedio = notas.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return promedio;
    }
    
    
    
    

    // Método para buscar alumnos por nombre o apellidos
    public List<Alumnos20> buscarAlumnosPorNombreApellidos(String nombre, String apellidos) {
        List<Alumnos20> alumnos = null;
        Session session = sessionFactory.openSession();
        try {
            alumnos = session.createQuery("FROM Alumnos20 WHERE nombre LIKE :nombre AND apellidos LIKE :apellidos", Alumnos20.class)
                            .setParameter("nombre", "%" + nombre + "%")
                            .setParameter("apellidos", "%" + apellidos + "%")
                            .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return alumnos;
    }
    
    
    
    

    // Método para actualizar las notas de un alumno
    public void actualizarNotasAlumno(int dni, List<Double> nuevasNotas) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Suponiendo que ya existe un método para obtener las notas de un alumno
            Notas notas = session.createQuery("FROM Notas WHERE dni = :dni", Notas.class)
                                .setParameter("dni", dni)
                                .getSingleResult();
            // Actualizar las notas
            notas.setNotas(nuevasNotas);
            session.update(notas);
            transaction.commit();
            System.out.println("Notas del alumno actualizadas correctamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

	     
	     
	     
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	     
	     
	     
	     
	     
	     
	 private static int readOption(Scanner scanner) {
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error al leer la opción. Intente de nuevo.");
            scanner.nextLine(); // Consumir la nueva línea pendiente en el buffer del scanner
        }
        return option;
    }
    
    
    
    
    
    
                 case 8:
                    // Actualizar notas de un alumno
                    
                    System.out.println("Ingrese el DNI del alumno para actualizar sus notas:");
                    
                    int dniActualizarNotas = teclado.nextInt();
                    // Supongamos que ya tienes la lista de nuevas notas a ingresar
                    
                    List<Double> nuevasNotas = obtenerNuevasNotas(); // Método ficticio para obtener nuevas notas
                    
                    operacionesAlumnos.actualizarNotasAlumno(dniActualizarNotas, nuevasNotas);
                  break;
    
    
    
    
    
                case 7:
                    // Buscar alumnos por nombre o apellidos
                    
                    System.out.println("Ingrese el nombre del alumno:");
                    
                    String nombreBuscar = teclado.next();
                    
                    System.out.println("Ingrese los apellidos del alumno:");
                    
                    String apellidosBuscar = teclado.next();
                    
                    List<Alumnos20> alumnosEncontrados = operacionesAlumnos.buscarAlumnosPorNombreApellidos(nombreBuscar, apellidosBuscar);
                    
                    if (alumnosEncontrados.isEmpty()) {
                        System.out.println("No se encontraron alumnos con ese nombre y apellidos.");
                    } else {
                        System.out.println("Alumnos encontrados:");
                        for (Alumnos20 alumno : alumnosEncontrados) {
                            System.out.println("DNI: " + alumno.getDni() +
                                    ", Nombre: " + alumno.getNombre() +
                                    ", Apellidos: " + alumno.getApellidos() +
                                    ", Teléfono: " + alumno.getTelefono());
                        }
    
    
    
    
    
    
	              case 5:
                    // Listar todos los alumnos
                    List<Alumnos20> listaAlumnos = operacionesAlumnos.listarAlumnos();
                    if (listaAlumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    }
                    break;
                    
                    
                    
                    
                    
                case 6:
                    // Calcular promedio de notas de un alumno
                    System.out.println("Ingrese el DNI del alumno para calcular su promedio de notas:");
                    int dniPromedio = teclado.nextInt();
                    double promedio = operacionesAlumnos.calcularPromedioNotas(dniPromedio);
                    System.out.println("El promedio de notas del alumno es: " + promedio);
                    break;
	     
	     
	     
	     
	     
	     
	     
	     
	     
	    */
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //
	    
	    
	}	