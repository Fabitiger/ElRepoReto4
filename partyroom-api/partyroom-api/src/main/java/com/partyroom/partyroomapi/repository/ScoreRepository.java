package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Score;
import com.partyroom.partyroomapi.repository.crud.ScoreCrudRepository;

@Repository
public class ScoreRepository {

	@Autowired
	private ScoreCrudRepository scoreCrudRepository;

	public List<Score> getAll() {
		return (List<Score>) scoreCrudRepository.findAll();

	}

	public Optional<Score> getScore(Integer id) {
		return scoreCrudRepository.findById(id);
	}

	public Score save(Score p) {
		return scoreCrudRepository.save(p);

	}
	
	public void delete(Integer id) {
		scoreCrudRepository.deleteById(id);

	}
}