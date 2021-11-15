package com.partyroom.partyroomapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "reservation")

@JsonPropertyOrder({"id", "startDate","devolutionDate", "status", "partyroom", "client", "score" })
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("idReservation")
	private Integer id;
	
	private Date startDate;

	private Date devolutionDate;

	private String status = "created";

	// foreign keys reservation

	@ManyToOne
	@JoinColumn(name = "client_ID", nullable = false)
	@JsonIgnoreProperties({"reservations", "messages"})
	private Client client;

	@ManyToOne
	@JoinColumn(name = "partyroom_ID", nullable = false)
	@JsonIgnoreProperties("reservations")
	private Partyroom partyroom;

	@OneToOne
	@JoinColumn(name = "score_ID", nullable = true)
	@JsonIgnoreProperties("reservation")
	private Score score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDevolutionDate() {
		return devolutionDate;
	}

	public void setDevolutionDate(Date devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Partyroom getPartyroom() {
		return partyroom;
	}

	public void setPartyroom(Partyroom partyroom) {
		this.partyroom = partyroom;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

}
