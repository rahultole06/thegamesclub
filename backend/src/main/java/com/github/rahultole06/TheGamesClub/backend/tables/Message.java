/**
 * Made by: Rahul M. Tole
 * Purpose: Store a single message between a sender and receiver
 */
package com.github.rahultole06.TheGamesClub.backend.tables;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;

	@ManyToOne
	  private User sender;

	@ManyToOne
	  private User receiver;

	@Column(nullable = false)
	  private String message;

	@Column(nullable = false)
	  private Date date;

	public Message() {

	}

	public Message(User sender, User receiver, String message, Date date) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
