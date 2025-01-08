/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the message table
 * */
package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.MessageService;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Date;

public class MessageController {

  private final MessageService messageService;

  public MessageController(final MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * Create a new message
   * */
  public int createMessage(User sender, User receiver, String message, Date date) {
    if (sender == null || receiver == null || message == null || date == null) { return -1; }
    try {
      Message newMessage = new Message(sender, receiver, message, date);
      return messageService.createMessage(newMessage);
    } catch (EntityExistsException e) {
      return -1;
    }
  }

  /**
   * Deletes a message
   * */
  public int deleteMessage(int id) {
    try {
      return messageService.deleteMessage(id);
    } catch (EntityNotFoundException e) {
      return -1;
    }
  }

  /**
   * Returns a message
   * */
  public Message getMessage(int id) {
    try {
      return messageService.getMessage(id);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

}
