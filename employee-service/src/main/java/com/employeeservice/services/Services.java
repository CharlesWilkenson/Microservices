package com.employeeservice.services;

import java.util.List;
import java.util.Optional;

import com.employeeservice.entities.Adress;
import com.employeeservice.entities.Employee;
import com.employeeservice.entities.Salary;

public interface Services {
public void saveEmploye(Employee e,Adress a,Salary salary);
public Employee findEmploye(String code);
public List<Employee> findAllEmployes();
public List<Employee> findByCode(String code);
public Optional<Employee> findByEmail(String email);
public void updateEmployes(Employee e,Adress a);
public void addAdress(Adress a);


public Salary getSalary(String code);
public void addSalary(String code,Salary salary);
public void updateSalary(Salary salary);
public List<Salary>allSalaries();

 
}
