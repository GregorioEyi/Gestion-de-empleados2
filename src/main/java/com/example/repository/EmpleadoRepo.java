package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import com.example.entities.Tarea;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Integer>{

}
   