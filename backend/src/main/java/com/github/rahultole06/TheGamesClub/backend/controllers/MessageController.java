/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the message table
 */
package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.MessageService;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class MessageController {

	private final MessageService messageService;

	public MessageController(final MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * Create a new message
	 * */
	@PostMapping("/messages")
	public int createMessage(int senderId, int receiverId, String message, Date date) {
		return messageService.createMessage(senderId, receiverId, message, date);
	}

	/**
	 * Deletes a message
	 * */
	@DeleteMapping("/messages/{id}")
	public int deleteMessage(@PathVariable int id) {
		try {
			return messageService.deleteMessage(id);
		} catch (EntityNotFoundException e) {
			return -1;
		}
	}

	/**
	 * Returns a message
	 * */
	@GetMapping("/messages/{id}")
	public Message getMessage(@PathVariable int id) {
		try {
			return messageService.getMessage(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

}
