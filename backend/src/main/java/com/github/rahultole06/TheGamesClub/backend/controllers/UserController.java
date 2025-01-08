/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the user table
 * */

package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.UserService;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public int createUser(String username, String password) {
    try {
      User newUser = new User(username, password);
      return userService.createUser(newUser);
    } catch (EntityExistsException e) {
      return -1;
    }
  }

  public int updateUser(String username, String oldPassword, String newPassword) {
    try {
      User newUser = new User(username, newPassword);
      return userService.updateUser(newUser, oldPassword);
    } catch (EntityNotFoundException | IllegalArgumentException e) {
      return -1;
    }
  }

  public int deleteUser(String username, String password) {
    try {
      return userService.deleteUser(username, password);
    } catch (EntityNotFoundException | IllegalArgumentException e) {
      return -1;
    }
  }

  public User getUser(String username) {
    try {
      return userService.getUserByUsername(username);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public List<Purchase> getUserPurchases(String username) {
    try {
      return userService.getUserPurchases(username);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public int buyGame(String username, Purchase purchase) {
    try {
      return userService.buyGame(username, purchase);
    } catch (EntityNotFoundException e) {
      return -1;
    }
  }

  public List<Game> getUserGamesAuthored(String username) {
    try {
      return userService.getUserGamesAuthored(username);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public int addUserGamesAuthored(String username, Game game) {
    try {
      return userService.addUserGamesAuthored(username, game);
    } catch (EntityNotFoundException e) {
      return -1;
    }
  }

  public List<Message> getUserMessages(String username) {
    try {
      return userService.getUserMessages(username);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public int addUserMessages(String username, Message message) {
    try {
      return userService.addUserMessages(username, message);
    } catch (EntityNotFoundException e) {
      return -1;
    }
  }
}
