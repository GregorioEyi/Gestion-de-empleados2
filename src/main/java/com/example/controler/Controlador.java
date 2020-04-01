package com.example.controler;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.BorrarApplication;
import com.example.entities.Empleado;
import com.example.entities.Tarea;
import com.example.paginas.Paginas;
import com.example.repository.EmpleadoRepo;
import com.example.services.Servicios;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller 
@RequestMapping("/home")
public class Controlador {    

	private Logger salida = LoggerFactory.getLogger(BorrarApplication.class);
	
	@Autowired
	private Servicios servicio;
	
	
	// ------------------ Ver tareas y empleados -------------------------
	@GetMapping(path = "/index") 
	public ModelAndView index(Model model) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		
		modelAndView.addObject("empleados", servicio.todosLosEmpleados());
		modelAndView.addObject("tareas", servicio.todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView; 
	}  
	 
	
	// ------------------ Ver empleado -------------------------
	
	@GetMapping(path = "/empleado")  
	public ModelAndView empleado(
				@RequestParam(defaultValue = "1", name = "id", required = false) int id
								) {
		
		salida.warn("El id entrante es: "+id);  
		ModelAndView modelAndView = new ModelAndView(Paginas.empleado);
				
		Empleado empleado = servicio.verEmpleado(id);
		salida.warn("Empleado: " + empleado.getNombre());
		
		modelAndView.addObject("empleado", servicio.verEmpleado(id));
		modelAndView.addObject("tareas", servicio.verEmpleado(id).getTareas());
		modelAndView.addObject("modEmpleado", servicio.verEmpleado(id));
		
		return modelAndView;
	}
	
	
	// ------------------ Alta de tareas y empleados -------------------------
	
	//Alta empleado
	@PostMapping(path = "/empleado/alta")
	public ModelAndView altaEmpleado(Empleado empleado) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("Empleado que se esta dando de alta: "+empleado.getNombre());
		Servicios.getMethod().addEmpleado(empleado);
		salida.warn("dado de alta: "+empleado.getNombre());
		modelAndView.addObject("empleados", servicio.todosLosEmpleados());
		modelAndView.addObject("tareas", servicio.todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView; 
	} 
	
	//Alta Tarea
	@PostMapping(path = "/tarea/alta")
	public ModelAndView altaTarea(Tarea tarea) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("Tarea que se esta dando de alta: "+tarea.getNombre());
		Servicios.getMethod().addTarea(tarea);
		salida.warn("dado de alta: "+tarea.getNombre());
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView;
	}
	
	// ------------------ Bajas de tareas y empleados -------------------------
	
	//Baja empleado
	@GetMapping(path = "/empleado/delete/{id}") //@PathVariable int id 
	public ModelAndView deleteEmpleado(@PathVariable  int id) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("ID ENTRANTE PARA SER BORRADO: "+ id);
		servicio.deleteEmpleado(id);
		salida.warn("USUARIO BORRADO: "+ id);
		modelAndView.addObject("empleados", servicio.todosLosEmpleados());
		modelAndView.addObject("tareas", servicio.todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView;
	}
	
	//Baja empleado
		@GetMapping(path = "/tarea/delete/{id}")
		public ModelAndView deleteTarea(@PathVariable  int id) {
			ModelAndView modelAndView = new ModelAndView(Paginas.index);
			salida.warn("ID ENTRANTE PARA SER BORRADO: "+ id);
			servicio.deleteTarea(id);
			salida.warn("TAREA BORRADO: "+ id);
			modelAndView.addObject("empleados", servicio.todosLosEmpleados());
			modelAndView.addObject("tareas", servicio.todosLasTareas());
			modelAndView.addObject("empleado", new Empleado());
			modelAndView.addObject("tarea", new Tarea());
			//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
			
			return modelAndView;
		}
	// ------------------ Actualiza de tareas y empleados ---------------------
//	@PutMapping(path = "/empleado")
//	public ModelAndView uptadeEmpleado(@RequestParam(name = "id", defaultValue = "1", required = false) int id) {
//	
//		ModelAndView modelAndView = new ModelAndView(Paginas.index);
//		return modelAndView;
//	}
	
	
	// ------------------ Asignar tarea a un empleado -------------------------
	
	
/*	@PostMapping(path = "/index")
	public ModelAndView altaTarea(@RequestParam(name = "id", defaultValue = "1", required = false) int id) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("Se esta asignando"+tarea.getNombre());
		Servicios.getMethod().addTarea(tarea);
		salida.warn("dado de alta: "+tarea.getNombre());
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		return modelAndView;
	}*/
		
}
