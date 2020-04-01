package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import com.example.entities.Tarea;

@Repository
public interface Empleado_Repo extends JpaRepository<Empleado, Integer>{

	@Query("SELECT i.nombre, i.descripcion, i.inicio, i.fin FROM tareas AS i WHERE i.id IN (SELECT e.id_tarea FROM empleado_tareas AS e WHERE e.id_Empleado =:id")
	List<Tarea> findByEmpleadoTarea(int id);
	
}
   