package org.ClientService.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.ClientService.entities.Adress;

import org.ClientService.entities.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@org.springframework.transaction.annotation.Transactional
public class ServiceImpl implements Services{
@Autowired
private ClientRepository clientRepository ;
@Autowired
private AdressRepository adressRepository;

	
	
	
	@Override
	public void saveClient(Client client, Adress adress) {
		adressRepository.save(adress);
		client.setAdress(adress);
		
		clientRepository.save(client);
		
	
		
	}
	
	

	@Override
	public Client findClient(String code) {
		Optional<Client>clientOptional=clientRepository.findById(code);
		
		Client c=new Client();
		if(clientOptional.isEmpty())
			throw new RuntimeException("Client not found");
		
		if(clientOptional.isPresent())			
		c=clientOptional.get();
		
		return c;
	}

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}
	
	
	@Override
	public void updateClient(Client client,Adress adress) {
		adressRepository.save(adress);
		client.setAdress(adress);
		
		clientRepository.save(client);
		
	}




}
