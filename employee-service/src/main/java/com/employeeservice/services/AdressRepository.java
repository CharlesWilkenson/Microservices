package com.employeeservice.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeservice.entities.Adress;

public interface AdressRepository extends JpaRepository<Adress, String>{

}
