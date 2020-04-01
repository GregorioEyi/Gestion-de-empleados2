package com.example.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.BorrarApplication;
import com.example.entities.Empleado;
import com.example.entities.Tarea;
import com.example.repository.EmpleadoRepo;
import com.example.repository.TareaRepo;

@Service
public class Servicios {
	
	private Logger salida = LoggerFactory.getLogger(BorrarApplication.class);
	
	private static Servicios servicio;
	
	@Autowired
	private static EmpleadoRepo empleadoRepo;

	@Autowired
	private static TareaRepo tareaRepo;
	
	
	public Servicios(EmpleadoRepo empl_repo, TareaRepo tarea_repo) {
		this.empleadoRepo = empl_repo;
		this.tareaRepo = tarea_repo;
		
	}
	
	//-------------------- inicio empleados---------------------//

	public List<Empleado> todosLosEmpleados() {
		return empleadoRepo.findAll();
	}
	
	public Empleado verEmpleado(int id) {
		Optional<Empleado> empleado;
		empleado = empleadoRepo.findById(id);
		return empleado.orElse(null);
	}
	
	public void addEmpleado(Empleado empleado) {
		empleadoRepo.save(empleado);
	}
	
	public void updateEmpleado(Empleado empleado) {
		empleadoRepo.save(empleado);
	}
	
	public void deleteEmpleado(int id) {
		empleadoRepo.deleteById(id);
	}
	
	//-------------------- fin Empleados---------------------//
	
	
	//-------------------- inicio tareas---------------------//
	public List<Tarea> todosLasTareas() { 
		return tareaRepo.findAll();
	}
	
	public Tarea verTarea(int id) {
		Optional<Tarea> tarea;
		tarea = tareaRepo.findById(id);
		return tarea.orElse(null);
	}
	
	public void addTarea(Tarea tarea) {
		tareaRepo.save(tarea);
	}
	
	public void updateTarea(Tarea tarea) {
		tareaRepo.save(tarea);
	}
	
	public void deleteTarea(int id) {
		tareaRepo.deleteById(id);
	}
	
	public void AsignarTarea(int codEmpleado, int codTarea) {
		// db
	}
	
	//-------------------- fin tareas---------------------//
	
	public static Servicios getMethod() {
		if(servicio == null) {
			servicio = new Servicios(empleadoRepo, tareaRepo);
		}	
		return servicio;
	}


}
