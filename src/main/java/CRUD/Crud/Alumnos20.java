package CRUD.Crud;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;




@Entity
public class Alumnos20 {

	
	
	
	 @Id
	 private int dni;
	 
	 
	    private String nombre;
	    
        private String apellidos;
        private int telefono;
        
       
	    
        
        
        
        
        
        
        
	    
	    // Métodos get y set para el atributo DNI
	    public int getDni() {
	        return dni;
	    }
	   
	    public void setDni(int dni) {
	        this.dni = dni;
	    }

	    
	    
	    
	    
	    // Métodos get y set para el atributo nombre
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    
	    
	    
	    
	    // Métodos get y set para el atributo nombre
	    public String getApellidos() {
	        return apellidos;
	    }

	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
	    }
	    
	    
	    
	    
	    // Métodos get y set para el atributo telefono
	    public int getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(int telefono) {
	        this.telefono = telefono;
	    }
	    
	    
	  
	    
	    
	    
	
}

	
	
	
	
	
	

