package com.example.controler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.repository.Empleado_Repo;
import com.example.services.Servicios;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller 
@RequestMapping("/home")
public class Controlador {    

	private Logger salida = LoggerFactory.getLogger(BorrarApplication.class);
	
	@GetMapping(path = "/index") 
	public ModelAndView index(Model model) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView; 
	}  
	 
	@GetMapping(path = "/empleado")  
	public ModelAndView empleado(
				@RequestParam(defaultValue = "1", name = "id", required = false) int id
								) {
		
		salida.warn("El id entrante es: "+id);  
		ModelAndView modelAndView = new ModelAndView(Paginas.empleado);
		
		List<Empleado> filtroEmpleado = Servicios.getMethod().todosLosEmpleados().stream().filter((p) ->{
			return p.getId() == id;
		}).collect(Collectors.toList());
		
	/*	List<Tarea> filtroTarea = Servicios.getMethod().todosLasTareas().stream().filter((p) ->{
			return p.getId() == id;
		}).collect(Collectors.toList());*/
		
		modelAndView.addObject("empleado", filtroEmpleado.get(0));
		modelAndView.addObject("tareas", Servicios.getMethod().tareaEmpleado(id));
		modelAndView.addObject("modEmpleado", filtroEmpleado.get(0));
		
		return modelAndView;
	}
	
	@PostMapping(path = "/index")
	public ModelAndView altaEmpleado(Empleado empleado) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("Empleado que se esta dando de alta: "+empleado.getNombre());
		Servicios.getMethod().addEmpleado(empleado);
		salida.warn("dado de alta: "+empleado.getNombre());
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView; 
	} 
	
	@PutMapping(path = "/empleado")
	public ModelAndView uptadeEmpleado(@RequestParam(name = "id", defaultValue = "1", required = false) int id) {
	
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		return modelAndView;
	}
	
	@DeleteMapping(path = "/{id}") //@PathVariable int id 
	public ModelAndView deleteEmpleado(@RequestParam(name = "id", defaultValue = "1", required = false) int id) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("ID ENTRANTE PARA SER BORRADO: "+ id);
		Servicios.getMethod().deleteEmpleado(id);
		salida.warn("USUARIO BORRADO: "+ id);
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		//modelAndView.addObject("asignarTarea", new EmpleadoTarea());
		return modelAndView;
	}

	
	
	/*@PostMapping(path = "/index")
	public ModelAndView altaTarea(Tarea tarea) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.warn("Tarea que se esta dando de alta: "+tarea.getNombre());
		Servicios.getMethod().addTarea(tarea);
		salida.warn("dado de alta: "+tarea.getNombre());
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		modelAndView.addObject("tarea", new Tarea());
		return modelAndView;
	}
	
	@PostMapping(path = "/index")
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
