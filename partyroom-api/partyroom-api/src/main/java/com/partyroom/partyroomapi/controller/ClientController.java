package com.partyroom.partyroomapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.partyroom.partyroomapi.model.Client;
import com.partyroom.partyroomapi.service.ClientService;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/all")
	public List<Client> getClients() {
		return clientService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional <Client> getClient(@PathVariable("id")Integer id){
		return clientService.getClient(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client p) {
		return clientService.save(p);
		
	}
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	 public Client replaceClient(@RequestBody Client newClient) {

		Optional<Client> client = clientService.getClient(newClient.getId());
		if (client.isPresent()) {
			Client client2 = client.get();
			client2.setName(newClient.getName());
			client2.setAge(newClient.getAge());
			client2.setPassword(newClient.getPassword());
			return clientService.save(client2);
		}else {
			return newClient;
		}

	}
	  @DeleteMapping("/{id}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public boolean deleteClient(@PathVariable Integer id) {
	    return clientService.delete(id);
	  }
	
}
