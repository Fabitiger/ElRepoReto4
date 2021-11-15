package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Message;
import com.partyroom.partyroomapi.repository.crud.MessageCrudRepository;

@Repository
public class MessageRepository {

	@Autowired
	private MessageCrudRepository messageCrudRepository;

	public List<Message> getAll() {
		return (List<Message>) messageCrudRepository.findAll();

	}

	public Optional<Message> getMessage(Integer id) {
		return messageCrudRepository.findById(id);
	}

	public Message save(Message p) {
		return messageCrudRepository.save(p);

	}

	public void delete(Integer id) {
		messageCrudRepository.deleteById(id);

	}
}