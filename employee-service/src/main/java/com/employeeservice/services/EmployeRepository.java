package com.employeeservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.employeeservice.entities.Employee;

//@RepositoryRestResource
public interface EmployeRepository extends JpaRepository<Employee, String>{
	
	@Query("SELECT e FROM Employee e where e.code=:x")
	public List<Employee> findByCode(@Param("x")String code);

//	@Query("SELECT e FROM Employee e where e.email=:x")
//	public List<Employee> findByEmail(@Param("x")String email);

	   Optional<Employee> findByEmail(String email);
	
	
}
