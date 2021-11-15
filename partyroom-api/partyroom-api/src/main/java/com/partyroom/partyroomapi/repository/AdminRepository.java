package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Admin;
import com.partyroom.partyroomapi.repository.crud.AdminCrudRepository;

@Repository
public class AdminRepository {

	@Autowired
	private AdminCrudRepository adminCrudRepository;

	public List<Admin> getAll() {
		return (List<Admin>) adminCrudRepository.findAll();

	}

	public Optional<Admin> getAdmin(Integer id) {
		return adminCrudRepository.findById(id);
	}

	public Admin save(Admin p) {
		return adminCrudRepository.save(p);
	}
	
	public void delete(Integer id) {
		adminCrudRepository.deleteById(id);
		
	}
	
}