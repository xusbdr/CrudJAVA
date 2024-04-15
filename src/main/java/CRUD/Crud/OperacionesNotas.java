package CRUD.Crud;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jakarta.persistence.NoResultException;

public class OperacionesNotas {


	
	 private SessionFactory sessionFactory;
	 
	  private OperacionesAlumnos operacionesAlumnos;
	 
	 
	  public OperacionesNotas() {
	        // Inicializar las sesiones de Hibernate
	        sessionFactory = new Configuration().configure().buildSessionFactory();
	        
	        operacionesAlumnos = new OperacionesAlumnos();
	  
	  }

	  
	  
	  // ------------
	  
	  
	  
	    
	    // -----------------
	    
	    

	    // Método para recuperar una nota por su ID
	  public void recuperarNota(int dni) {
	  
	    	 Session session = sessionFactory.openSession();
	    	    Transaction transaction = null;

	    	    try {
	    	        transaction = session.beginTransaction();
	    	        Notas notas = session.createQuery("FROM Notas WHERE dni = :dni", Notas.class)
	    	                            .setParameter("dni", dni)
	    	                            .getSingleResult();
	    	        transaction.commit();

	    	        if (notas != null) {
	    	            // Mostrar las notas del alumno
	    	        	
	    	        	System.out.println("");
	    	        	System.out.println("LAS NOTAS ORDENADAS POR TRIMESTRES SON :");
	    	            System.out.println("Notas del primer trimestre: " + notas.getPrimerTrimestre());
	    	            System.out.println("Notas del segundo trimestre: " + notas.getSegundoTrimestre());	    	            
	    	            System.out.println("Notas del tercer trimestre: " + notas.getTercerTrimestre());
	    	            System.out.println("");
	    	        } else {
	    	            System.out.println("No se encontraron notas para el alumno con DNI: " + dni);
	    	        }
	    	    } catch (NoResultException e) {
	    	        System.out.println("No se encontraron notas para el alumno con DNI: " + dni);
	    	    } catch (Exception e) {
	    	        if (transaction != null) {
	    	            transaction.rollback();
	    	        }
	    	        e.printStackTrace();
	    	    } finally {
	    	        session.close();
	    	    }
	    }
	    
	    
	    // ----------------
	    
	    

	    // Método para actualizar una nota
	    
	    public void actualizarNota(int dni) {
	        
	    	 Session session = sessionFactory.openSession();
	    	    Transaction transaction = null;
	    	    Scanner scanner = new Scanner(System.in);

	    	    try {
	    	        transaction = session.beginTransaction();
	    	        
	    	        Notas notas = session.get(Notas.class, dni);
	    	        
	    	        if (notas != null) {
	    	            
	    	         //   scanner.nextLine(); // Consumir la nueva línea pendiente
	    	            
	    	            System.out.println("Ingrese notas del 1º Trimestre :");
	    	            int primero = Integer.parseInt(scanner.nextLine());
	    	            
	    	            System.out.println("Ingrese notas del 2º Trimestre :");
	    	            int segundo = Integer.parseInt(scanner.nextLine());
	    	            
	    	            System.out.println("Ingrese notas del 3º Trimestre :");
	    	            int tercero = Integer.parseInt(scanner.nextLine());
	    	            
	    	         
	    	            notas.setPrimerTrimestre(primero);
	    	            notas.setSegundoTrimestre(segundo);
	    	            notas.setTercerTrimestre(tercero);
	    	            
	    	            session.update(notas);
	    	            
	    	            transaction.commit();
	    	            
	    	            System.out.println("ALUMNO ACTUALIZADO CORRECTAMENTE.");
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
	    	        scanner.close();
	    	    }
	    }
	    
	    
	    // --------------
	    
	    
	    
	    
	}



//