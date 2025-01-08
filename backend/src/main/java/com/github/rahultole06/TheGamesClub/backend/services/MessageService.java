/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the messages table
 * */

package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.MessageRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final MessageRepo messageRepo;

  public MessageService(MessageRepo messageRepo) {
    this.messageRepo = messageRepo;
  }

  @Transactional
  public void createMessage(Message message) {
    if (messageRepo.existsById(message.getId())) {
      throw new EntityExistsException("Message already exists");
    }
    messageRepo.save(message);
  }

  @Transactional
  public void deleteMessage(Message message) {
    if (!messageRepo.existsById(message.getId())) {
      throw new EntityNotFoundException("Message not found");
    }
    messageRepo.deleteById(message.getId());
  }

  @Transactional
  public Message getMessage(int id) {
    if (!messageRepo.existsById(id)) {
      throw new EntityNotFoundException("Message not found");
    }
    return messageRepo.findById(id);
  }

}
