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

import com.partyroom.partyroomapi.model.Message;
import com.partyroom.partyroomapi.service.MessageService;


@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/all")
	public List<Message> getMessages() {
		return messageService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional <Message> getMessage(@PathVariable("id")Integer id){
		return messageService.getMessage(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Message save(@RequestBody Message p) {
		return messageService.save(p);
		
	}
	
@PutMapping("/update")
@ResponseStatus(HttpStatus.CREATED)
public Message replaceMessage(@RequestBody Message newMessage) {

	Optional<Message> message = messageService.getMessage(newMessage.getId());
	if (message.isPresent()) {
		Message message2 = message.get();
		message2.setMessagetext(newMessage.getMessagetext());

		return messageService.save(message2);
	} else {
		return newMessage;
	}

}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public boolean deleteMessage(@PathVariable Integer id) {
	return messageService.delete(id);
}

}