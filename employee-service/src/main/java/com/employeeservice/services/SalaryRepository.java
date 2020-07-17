package com.employeeservice.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeservice.entities.Salary;

public interface SalaryRepository extends JpaRepository<Salary, String> {

}
