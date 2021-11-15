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

import com.partyroom.partyroomapi.model.Reservation;
import com.partyroom.partyroomapi.service.ReservationService;


@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/all")
	public List<Reservation> getReservations() {
		return reservationService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional <Reservation> getReservation(@PathVariable("id")Integer id){
		return reservationService.getReservation(id);
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation save(@RequestBody Reservation p) {
		return reservationService.save(p);
		
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation replaceReservation(@RequestBody Reservation newReservation) {

		Optional<Reservation> reservation = reservationService.getReservation(newReservation.getId());
		if (reservation.isPresent()) {
			Reservation reservation2 = reservation.get();
			reservation2.setStartDate(newReservation.getStartDate());
			reservation2.setDevolutionDate(newReservation.getDevolutionDate());
			reservation2.setStatus(newReservation.getStatus());
			return reservationService.save(reservation2);
		} else {
			return newReservation;
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteReservation(@PathVariable Integer id) {
		return reservationService.delete(id);
	}

}