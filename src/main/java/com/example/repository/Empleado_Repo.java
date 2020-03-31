package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;

@Repository
public interface Empleado_Repo extends JpaRepository<Empleado, Integer>{
	
}
   