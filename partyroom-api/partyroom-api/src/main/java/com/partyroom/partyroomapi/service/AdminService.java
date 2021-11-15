package com.partyroom.partyroomapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.partyroomapi.model.Admin;
import com.partyroom.partyroomapi.repository.AdminRepository;

@Service
public class AdminService {


	@Autowired
	private AdminRepository adminRepository;
	public List<Admin> getAll() {
		return adminRepository.getAll();
	}

	public Optional<Admin> getAdmin(Integer id) {
		return adminRepository.getAdmin(id);
	}

	public Admin save(Admin p) {
		if (p.getId() == null) {
			return adminRepository.save(p);
		} else {
			Optional<Admin> caux = adminRepository.getAdmin(p.getId());
			if (caux.isPresent()) {
				return adminRepository.save(p);
			} else {
				return p;
			}
		}
	}
	
	public boolean delete (Integer id) {
		Optional<Admin> caux = adminRepository.getAdmin(id);
		if (caux.isPresent()) {
			 adminRepository.delete(id);
			 return true;
		} else {
			return false;
		}
		
		
	}
	
}
