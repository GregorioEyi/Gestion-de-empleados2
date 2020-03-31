package com.example.controler;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.BorrarApplication;
import com.example.entities.Empleado;
import com.example.entities.Tarea;
import com.example.paginas.Paginas;
import com.example.services.Servicios;

@Controller
public class Controlador { 

	private Logger salida = LoggerFactory.getLogger(BorrarApplication.class);
	
	@RequestMapping("/index")  
	public ModelAndView index(Model model) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		modelAndView.addObject("empleado", new Empleado());
		return modelAndView; 
	}  
	
	@RequestMapping("/empleado") 
	public ModelAndView empleado(
				@RequestParam(defaultValue = "1", name = "id", required = false) int id
								) {
		
		salida.warn("El id entrante es: "+id);
		ModelAndView modelAndView = new ModelAndView(Paginas.empleado);
		
		List<Empleado> filtroEmpleado = Servicios.getMethod().todosLosEmpleados().stream().filter((p) ->{
			return p.getId() == id;
		}).collect(Collectors.toList());
		
		List<Tarea> filtroTarea = Servicios.getMethod().todosLasTareas().stream().filter((p) ->{
			return p.getId() == id;
		}).collect(Collectors.toList());
		
		modelAndView.addObject("empleado", filtroEmpleado.get(0));
		modelAndView.addObject("tarea", filtroTarea.get(0));
		
		return modelAndView;
	}
	
	//@RequestMapping("/hola3")
/*	@PostMapping("/index")
	public ModelAndView altaEmpleado(Empleado empleado) {
		ModelAndView modelAndView = new ModelAndView(Paginas.index);
		salida.info("Empleado que se esta dando de alta: "+empleado.getNombre());
		Servicios.getMethod().addEmpleado(empleado);
		modelAndView.addObject("empleados", Servicios.getMethod().todosLosEmpleados());
		modelAndView.addObject("tareas", Servicios.getMethod().todosLasTareas());
		//modelAndView.addObject("empleado", Servicios.getMethod().todosLosEmpleados());
		return modelAndView;
	}*/
	
	@RequestMapping("/hola4")
	public String saludo4() {
		
		return Paginas.index2;
	}
}
