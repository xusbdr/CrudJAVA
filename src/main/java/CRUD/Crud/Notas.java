package CRUD.Crud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Notas {

	
	 @Id
	 private int dni;  // Aquí establecemos el DNI como la clave primaria

	  
	    private int primerTrimestre;
	    private int segundoTrimestre;
	    private int tercerTrimestre;
	    
	    
	
	
	 // Getters y setters

	  
	    public int getDni() {
	        return dni;
	    }
	    public void setDni(int dni) {
	        this.dni = dni;
	    }
	    
	   
	    
	    
	 
	    
	    public int getPrimerTrimestre() {
	        return primerTrimestre;
	    }
	    public void setPrimerTrimestre(int primerTrimestre) {
	        this.primerTrimestre = primerTrimestre;
	    }

	    
	    public int getSegundoTrimestre() {
	        return segundoTrimestre;
	    }
	    public void setSegundoTrimestre(int segundoTrimestre) {
	        this.segundoTrimestre = segundoTrimestre;
	    }

	   
	    public int getTercerTrimestre() {
	        return tercerTrimestre;
	    }
	    public void setTercerTrimestre(int tercerTrimestre) {
	        this.tercerTrimestre = tercerTrimestre;
	    }
		
	    
	    
	  
	    
	    
	}




/*                                

 @Entity: Esta anotación se utiliza para marcar una clase como una entidad persistente
 
 @Entity
 public class Producto {
    // Atributos y métodos de la clase
}
 
 
 

 
 @Id: Esta anotación se utiliza para marcar un atributo como la clave primaria de la entidad
   @Id
   private Long id;



@GeneratedValue: Esta anotación se utiliza para especificar cómo se generará el valor de la clave primaria.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



@Column: Esta anotación se utiliza para especificar la columna de la base de datos a la que está mapeado 
      @Column(name = "nombre_producto", length = 50, nullable = false)
      private String nombre;
 
 
 
 
 @OneToMany: Esta anotación se utiliza para mapear una relación uno a muchos entre dos entidades. Indica que una instancia de la entidad actual puede tener múltiples instancias de la entidad asociada
        @OneToMany(mappedBy = "categoria")
        private List<Producto> productos;





 @ManyToOne: Esta anotación se utiliza para mapear una relación muchos a uno entre dos entidades. Indica que varias instancias de la entidad actual pueden estar asociadas a una sola instancia de la entidad relacionada.
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;



@OneToOne: Esta anotación se utiliza para mapear una relación uno a uno entre dos entidades. Indica que una instancia de la entidad actual está asociada a una única instancia de la entidad relacionada
     @OneToOne
     @JoinColumn(name = "detalle_id")
     private Detalle detalle;



@JoinColumn: Esta anotación se utiliza para especificar la columna de la base de datos que se utilizará para mapear la relación entre dos entidades.
     @ManyToOne
     @JoinColumn(name = "categoria_id", nullable = false)
     private Categoria categoria;



@Transient: Esta anotación se utiliza para especificar que un atributo de la entidad no debe ser persistido en la base de datos
     @Transient
     private String nombreCompleto;


@Temporal: Esta anotación se utiliza para mapear atributos de tipo Date o Calendar a tipos de datos temporales en la base de datos, como DATE, TIME o TIMESTAMP
     @Temporal(TemporalType.DATE)
     private Date fechaNacimiento;


 
  
 */















// 