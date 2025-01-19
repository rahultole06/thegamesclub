/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the messages table
 */

package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.MessageRepo;
import com.github.rahultole06.TheGamesClub.backend.repos.UserRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

	private final MessageRepo messageRepo;
	private final UserRepo userRepo;

	public MessageService(MessageRepo messageRepo, UserRepo userRepo) {
		this.messageRepo = messageRepo;
		this.userRepo = userRepo;
	}

	@Transactional
	public int createMessage(int senderId, int receiverId, String message, Date date) {
		if (!userRepo.existsById(senderId)) {
			throw new EntityNotFoundException("Sender not found");
		} else if (!userRepo.existsById(receiverId)) {
			throw new EntityNotFoundException("Receiver not found");
		}
		User sender = userRepo.findById(senderId);
		User receiver = userRepo.findById(receiverId);
		Message msg = new Message(sender, receiver, message, date);
		messageRepo.save(msg);
		return 0;
	}

	@Transactional
	public int deleteMessage(int id) {
		if (!messageRepo.existsById(id)) {
			throw new EntityNotFoundException("Message not found");
		}
		messageRepo.deleteById(id);
		return 0;
	}

	@Transactional
	public Message getMessage(int id) {
		if (!messageRepo.existsById(id)) {
			throw new EntityNotFoundException("Message not found");
		}
		return messageRepo.findById(id);
	}

}
