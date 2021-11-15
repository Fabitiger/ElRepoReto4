package com.partyroom.partyroomapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.partyroomapi.model.Category;
import com.partyroom.partyroomapi.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getAll() {
		return categoryRepository.getAll();
	}

	public Optional<Category> getCategory(Integer id) {
		return categoryRepository.getCategory(id);
	}

	public Category save(Category p) {
		if (p.getId() == null) {
			return categoryRepository.save(p);
		} else {
			Optional<Category> caux = categoryRepository.getCategory(p.getId());
			if (caux.isPresent()) {
				return categoryRepository.save(p);
			} else {
				return p;
			}
		}
	}
	public boolean delete (Integer id) {
		Optional<Category> caux = categoryRepository.getCategory(id);
		if (caux.isPresent()) {
			 categoryRepository.delete(id);
			 return true;
		} else {
			return false;
		}
		
		
	}

}


