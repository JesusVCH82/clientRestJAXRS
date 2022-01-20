package com.client.rest;


import com.client.rest.models.Cliente;
import com.client.rest.service.ClienteServiceImpl;

public class ClienteRun {
	
	public static void main(String[] args) throws Exception {
		ClienteServiceImpl clientService = new ClienteServiceImpl();		
		Cliente c = new Cliente(1, "jesus", "vazquez", 28, "JVCH1112221234", "Hidalgo 62", "554431981");
	
		//GET
		System.out.println("GET: " + clientService.getClient(1));
		
		//GET
		System.out.println("GET: " + clientService.getClients());
		
		//POST
		System.out.println("POST: " + clientService.saveClient(c));
		
		//PUT
		System.out.println("PUT: " + clientService.updateClient(1, c));
		
		//DELETE
		System.out.println("PUT: " + clientService.deleteClient(1));	
	}
}
