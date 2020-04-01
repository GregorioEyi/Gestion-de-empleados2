package com.example.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
/*@NamedQueries({
    @NamedQuery(name = "empleados.findAll", query = "SELECT * FROM empleados")
})*/
public class Empleado implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Es como el primary key con el autoincrement
	private int id;
	
	@Column(name = "nombre", length = 100)
	private String nombre;
	
	@Column(name = "apellido1", length = 100) 
	private String apellido1;
	
	@Column(name = "apellido2", length = 100)
	private String apellido2;
	
	@Column(name = "dni", length = 9)
	private String dni;
	
	@Column(name = "alta", length = 6)
	private String alta;
	
	@Column(name = "baja", length = 6)
	private String baja;
	
	@JoinColumn(name = "id_Empleado", unique = true)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="empleado_tareas"
				,joinColumns = @JoinColumn(name="id_Empleado")
				,inverseJoinColumns = @JoinColumn(name =" id_tarea"))
	private List<Tarea> tareas;

	public Empleado() {
		
	}
	
	public Empleado(int id, String nombre, String apellido1, String apellido2, String dni, String alta, String baja,
			List<Tarea> tareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.alta = alta;
		this.baja = baja;
		this.tareas = tareas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}

	public String getBaja() {
		return baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
