package com.client.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import com.client.rest.models.Cliente;
import com.google.gson.Gson;

public class ClienteServiceImpl {
	
	private static final String PATH_API = "http://192.168.1.80:8080/serviceREST-JAX-RS/api/v1/clientes";

	/**
	 * Metodo para obtener un cliente por id
	 * GET /clientes/{id}
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Cliente getClient(int id) throws Exception {
		String ruta = PATH_API + "/"+id;
		ClientRequest request = new ClientRequest(ruta);
		request.header("KEY_SECRET", "JVCH").accept(MediaType.APPLICATION_JSON);
		//Peticion tipo get
		ClientResponse<String> respuestaCliente = request.get(String.class);
		if(respuestaCliente.getStatus() != 200 ) {
			throw new RuntimeException("Error al realizar la peticion: "
					+ respuestaCliente.getStatus());
		}
		//Se obtiene la entidad y se convierte tipo Cliente
		Cliente  cliente = new Gson().fromJson(respuestaCliente.getEntity(), Cliente.class);
		return cliente;
	}
	
	/**
	 * Metodo para obtener todos los clientes
	 * GET /clientes
	 * @return
	 * @throws Exception
	 */
	public List<Cliente> getClients() throws Exception{
		List<Cliente> clientes = new ArrayList<Cliente>();
		ClientRequest request = new ClientRequest(PATH_API);
		request.header("KEY_SECRET", "JVCH").accept(MediaType.APPLICATION_JSON);
		ClientResponse<String> respuestaClientes = request.get(String.class);
		Cliente[]  clientesArray = new Gson().fromJson(respuestaClientes.getEntity(), Cliente[].class);
		clientes = Arrays.asList(clientesArray);
		return clientes;
	}
	
	/**
	 * Metodo para crear un nuevo cliente
	 * POST /clientes
	 * @param cliente
	 * @return
	 * @throws Exception
	 */
	public Cliente saveClient(Cliente cliente) throws Exception {
		ClientRequest request = new ClientRequest(PATH_API);
		request.accept(MediaType.APPLICATION_JSON);
		String clienteJson = new Gson().toJson(cliente);
		request.body(MediaType.APPLICATION_JSON, clienteJson).header("KEY_SECRET", "JVCH").accept(MediaType.TEXT_PLAIN);
		ClientResponse<String> respuestaCliente = request.post(String.class);
		return cliente;
	}
	
	/**
	 * Metodo para actualizar un cliente por id
	 * PUT /clientes/{id}
	 * @param id
	 * @param cliente
	 * @return
	 * @throws Exception
	 */
	public Cliente updateClient(int id, Cliente cliente) throws Exception {
		String ruta = PATH_API + "/"+id;
		ClientRequest request = new ClientRequest(ruta);
		request.header("KEY_SECRET", "JVCH").accept(MediaType.APPLICATION_JSON);
		String clienteJson = new Gson().toJson(cliente);
		request.body(MediaType.APPLICATION_JSON, clienteJson).header("KEY_SECRET", "JVCH").accept(MediaType.TEXT_PLAIN);
		ClientResponse<String> respuestaCliente = request.put(String.class);
		return cliente;
	}
	
	/**
	 * Metodo para eliminar un cliente por id
	 * DELETE /clientes/{id}
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String deleteClient(int id) throws Exception {
		String ruta = PATH_API + "/"+id;
		ClientRequest request = new ClientRequest(ruta);
		request.header("KEY_SECRET", "JVCH").accept(MediaType.APPLICATION_JSON);
		ClientResponse<String> respuestaCliente = request.delete(String.class);
		return respuestaCliente.getEntity();
	}
}
