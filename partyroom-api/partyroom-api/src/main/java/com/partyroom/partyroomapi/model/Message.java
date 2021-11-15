package com.partyroom.partyroomapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "message")
@JsonPropertyOrder({"id", "messagetext","partyroom", "client"})
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("idMessage")
	private Integer id;

	@JsonProperty("messageText")
	private String messagetext;

	// foreign keys message
	@ManyToOne
	@JoinColumn(name = "client_ID", nullable = false)
	@JsonIgnoreProperties({"messages", "reservations"})
	private Client client;

	@ManyToOne
	@JoinColumn(name = "partyroom_ID", nullable = false)
	@JsonIgnoreProperties({"messages", "reservations"})
	private Partyroom partyroom;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
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

}
