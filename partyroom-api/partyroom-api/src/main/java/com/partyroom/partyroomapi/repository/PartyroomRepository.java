package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Partyroom;
import com.partyroom.partyroomapi.repository.crud.PartyroomCrudRepository;

@Repository
public class PartyroomRepository {

	@Autowired
	private PartyroomCrudRepository partyroomCrudRepository;

	public List<Partyroom> getAll() {
		return (List<Partyroom>) partyroomCrudRepository.findAll();

	}

	public Optional<Partyroom> getPartyroom(Integer id) {
		return partyroomCrudRepository.findById(id);
	}

	public Partyroom save(Partyroom p) {
		return partyroomCrudRepository.save(p);

	}
	
	public void delete(Integer id) {
		partyroomCrudRepository.deleteById(id);

	}
}