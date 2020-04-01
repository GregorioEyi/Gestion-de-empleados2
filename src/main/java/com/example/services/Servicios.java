package com.example.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BorrarApplication;
import com.example.entities.Empleado;
import com.example.entities.Tarea;
import com.example.repository.Empleado_Repo;
import com.example.repository.Tarea_repo;

@Service
public class Servicios {
	
	private Logger salida = LoggerFactory.getLogger(BorrarApplication.class);
	
	private static Servicios servicio;
	
	private static Empleado_Repo empl_repo;
	
	private static Tarea_repo tarea_repo;
	
	
	@Autowired
	public Servicios(Empleado_Repo empl_repo, Tarea_repo tarea_repo) {
		this.empl_repo = empl_repo;
		this.tarea_repo = tarea_repo;
	}
	
	//-------------------- inicio empleados---------------------//

	public List<Empleado> todosLosEmpleados() {
		return empl_repo.findAll();
	}
	
	public Empleado verEmpleado(int id) {
		Empleado empleado = new Empleado();
		empl_repo.findById(id);
		//rellenar el empleado
		return empleado;
	}
	
	public void addEmpleado(Empleado empleado) {
		empl_repo.save(empleado);
	}
	
	public void updateEmpleado(Empleado empleado) {
		empl_repo.save(empleado);
	}
	
	public void deleteEmpleado(int id) {
		empl_repo.deleteById(id);
	}
	
	//-------------------- fin Empleados---------------------//
	
	
	//-------------------- inicio tareas---------------------//
	public List<Tarea> todosLasTareas() { 
		return tarea_repo.findAll();
	}
	
	public List<Tarea> tareaEmpleado(int id) {
		List<Tarea> ListaTareasEmpleado = empl_repo.findByEmpleadoTarea(id);
		return ListaTareasEmpleado;
	}
	
	public void addTarea(Tarea tarea) {
		tarea_repo.save(tarea);
	}
	
	public void updateTarea(Tarea tarea) {
		tarea_repo.save(tarea);
	}
	
	public void deleteTarea(int id) {
		tarea_repo.deleteById(id);
	}
	
	public void AsignarTarea(int codEmpleado, int codTarea) {
		// db
	}
	
	//-------------------- fin tareas---------------------//
	
	public static Servicios getMethod() {
		if(servicio == null) {
			servicio = new Servicios(empl_repo, tarea_repo);
		}	
		return servicio;
	}


}
