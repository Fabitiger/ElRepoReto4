package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Client;
import com.partyroom.partyroomapi.repository.crud.ClientCrudRepository;

@Repository
public class ClientRepository {

	@Autowired
	private ClientCrudRepository clientCrudRepository;

	public List<Client> getAll() {
		return (List<Client>) clientCrudRepository.findAll();

	}

	public Optional<Client> getClient(Integer id) {
		return clientCrudRepository.findById(id);
	}

	public Client save(Client p) {
		return clientCrudRepository.save(p);

	}
	
	public void delete(Integer id) {
		clientCrudRepository.deleteById(id);

	}
}