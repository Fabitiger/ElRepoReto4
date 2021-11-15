package com.partyroom.partyroomapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.partyroomapi.model.Message;
import com.partyroom.partyroomapi.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> getAll() {
		return messageRepository.getAll();
	}

	public Optional<Message> getMessage(Integer id) {
		return messageRepository.getMessage(id);
	}

	public Message save(Message p) {
		if (p.getId() == null) {
			return messageRepository.save(p);
		} else {
			Optional<Message> caux = messageRepository.getMessage(p.getId());
			if (caux.isPresent()) {
				return messageRepository.save(p);
			} else {
				return p;
			}
		}
	}
	public boolean delete (Integer id) {
		Optional<Message> caux = messageRepository.getMessage(id);
		if (caux.isPresent()) {
			 messageRepository.delete(id);
			 return true;
		} else {
			return false;
		}
		
		
	}

}