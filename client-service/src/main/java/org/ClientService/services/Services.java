package org.ClientService.services;

import java.util.List;


import org.ClientService.entities.Adress;

import org.ClientService.entities.Client;


public interface Services {

	
	public void saveClient(Client client,Adress adress);
	public Client findClient(String code);
	public List<Client> findAllClients();
	public void updateClient(Client client,Adress adress);
	

}
