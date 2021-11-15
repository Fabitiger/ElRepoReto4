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

import com.partyroom.partyroomapi.model.Category;
import com.partyroom.partyroomapi.service.CategoryService;

@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public List<Category> getCategorys() {
		return categoryService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional <Category> getCategory(@PathVariable("id")Integer id){
		return categoryService.getCategory(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Category save(@RequestBody Category p) {
		return categoryService.save(p);
		
	}
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	 public Category replaceCategory(@RequestBody Category newCategory) {

		Optional<Category> category = categoryService.getCategory(newCategory.getId());
		if (category.isPresent()) {
			Category category2 = category.get();
			category2.setName(newCategory.getName());
			category2.setDescription(newCategory.getDescription());
			return categoryService.save(category2);
		}else {
			return newCategory;
		}

	}
	  @DeleteMapping("/{id}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public boolean deleteCategory(@PathVariable Integer id) {
	    return categoryService.delete(id);
	  }
	
}
	