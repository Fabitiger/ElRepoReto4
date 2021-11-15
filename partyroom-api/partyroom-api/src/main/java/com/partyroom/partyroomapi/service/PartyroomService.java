package com.partyroom.partyroomapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.partyroomapi.model.Partyroom;
import com.partyroom.partyroomapi.repository.PartyroomRepository;

@Service
public class PartyroomService {
	
	@Autowired
	private PartyroomRepository partyroomRepository;

	public List<Partyroom> getAll() {
		return partyroomRepository.getAll();
	}

	public Optional<Partyroom> getPartyroom(Integer id) {
		return partyroomRepository.getPartyroom(id);
	}

	public Partyroom save(Partyroom p) {
		if (p.getId() == null) {
			return partyroomRepository.save(p);
		} else {
			Optional<Partyroom> caux = partyroomRepository.getPartyroom(p.getId());
			if (caux.isPresent()) {
				return partyroomRepository.save(p);
			} else {
				return p;
			}
		}
	}
	public boolean delete (Integer id) {
		Optional<Partyroom> caux = partyroomRepository.getPartyroom(id);
		if (caux.isPresent()) {
			 partyroomRepository.delete(id);
			 return true;
		} else {
			return false;
		}
		
		
	}

}


