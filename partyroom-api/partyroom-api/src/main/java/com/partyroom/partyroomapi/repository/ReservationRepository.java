package com.partyroom.partyroomapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partyroom.partyroomapi.model.Reservation;
import com.partyroom.partyroomapi.repository.crud.ReservationCrudRepository;

@Repository
public class ReservationRepository {

	@Autowired
	private ReservationCrudRepository reservationCrudRepository;

	public List<Reservation> getAll() {
		return (List<Reservation>) reservationCrudRepository.findAll();

	}

	public Optional<Reservation> getReservation(Integer id) {
		return reservationCrudRepository.findById(id);
	}

	public Reservation save(Reservation p) {
		return reservationCrudRepository.save(p);

	}
	
	
	public void delete(Integer id) {
		reservationCrudRepository.deleteById(id);

	}
}