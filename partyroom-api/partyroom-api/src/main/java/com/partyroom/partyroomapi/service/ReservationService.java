package com.partyroom.partyroomapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyroom.partyroomapi.model.Reservation;
import com.partyroom.partyroomapi.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	public List<Reservation> getAll() {
		return reservationRepository.getAll();
	}

	public Optional<Reservation> getReservation(Integer id) {
		return reservationRepository.getReservation(id);
	}

	public Reservation save(Reservation p) {
		if (p.getId() == null) {
			return reservationRepository.save(p);
		} else {
			Optional<Reservation> caux = reservationRepository.getReservation(p.getId());
			if (caux.isPresent()) {
				return reservationRepository.save(p);
			} else {
				return p;
			}
		}
	}
	public boolean delete (Integer id) {
		Optional<Reservation> caux = reservationRepository.getReservation(id);
		if (caux.isPresent()) {
			 reservationRepository.delete(id);
			 return true;
		} else {
			return false;
		}
		
		
	}

}
