package com.partyroom.partyroomapi.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.partyroom.partyroomapi.model.Reservation;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {

}
