package com.partyroom.partyroomapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.partyroomapi.model.Client;
import com.partyroom.partyroomapi.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getAll() {
		return clientRepository.getAll();
	}

	public Optional<Client> getClient(Integer id) {
		return clientRepository.getClient(id);
	}

	public Client save(Client p) {
		if (p.getId() == null) {
			return clientRepository.save(p);
		} else {
			Optional<Client> caux = clientRepository.getClient(p.getId());
			if (caux.isPresent()) {
				return clientRepository.save(p);
			} else {
				return p;
			}
		}
	}
	public boolean delete (Integer id) {
		Optional<Client> caux = clientRepository.getClient(id);
		if (caux.isPresent()) {
			 clientRepository.delete(id);
			 return true;
		} else {
			return false;
		}
		
		
	}

}
