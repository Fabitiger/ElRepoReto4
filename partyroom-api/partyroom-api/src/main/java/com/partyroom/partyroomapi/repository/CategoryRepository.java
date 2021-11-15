package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Category;
import com.partyroom.partyroomapi.repository.crud.CategoryCrudRepository;

@Repository
public class CategoryRepository {

	@Autowired
	private CategoryCrudRepository categoryCrudRepository;

	public List<Category> getAll() {
		return (List<Category>) categoryCrudRepository.findAll();

	}

	public Optional<Category> getCategory(Integer id) {
		return categoryCrudRepository.findById(id);
	}

	public Category save(Category p) {
		return categoryCrudRepository.save(p);

	}
	
	public void delete(Integer id) {
		categoryCrudRepository.deleteById(id);

	}
}

