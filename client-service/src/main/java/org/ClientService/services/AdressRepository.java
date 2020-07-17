package org.ClientService.services;

import org.ClientService.entities.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, String>{

}
