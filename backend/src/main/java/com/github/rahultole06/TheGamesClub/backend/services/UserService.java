/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the user table
 * */
package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.UserRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepo userRepo;
  private final GameService gameService;
  private final MessageService messageService;

  public UserService(UserRepo userRepo, GameService gameService, MessageService messageService) {
    this.userRepo = userRepo;
    this.gameService = gameService;
    this.messageService = messageService;
  }

  @Transactional
  public int createUser(User user) {
    if (userRepo.existsByUsername(user.getUsername())) {
      throw new EntityExistsException("User already exists");
    }
    userRepo.save(user);
    return 0;
  }

  @Transactional
  public int updateUser(int userId, String newUsername, String oldPassword, String newPassword) {
    User existingUser = userRepo.findById(userId);
    if (existingUser == null) {
      throw new EntityNotFoundException("User not found");
    } else if (!existingUser.getPassword().equals(oldPassword)) {
      throw new IllegalArgumentException("Wrong password");
    }
    existingUser.setUsername(newUsername);
    existingUser.setPassword(newPassword);
    userRepo.save(existingUser);
    return 0;
  }

  @Transactional
  public int deleteUser(int userId, String password) {
    if (!userRepo.existsById(userId)) {
      throw new EntityNotFoundException("User not found");
    } else if (!userRepo.findById(userId).getPassword().equals(password)) {
      throw new IllegalArgumentException("Wrong password");
    }
    userRepo.deleteById(userId);
    return 0;
  }

  @Transactional
  public User getUserById(int id) {
    if (!userRepo.existsById(id)) {
      throw new EntityNotFoundException("User not found");
    }
    return userRepo.findById(id);
  }

  @Transactional
  public List<Purchase> getUserPurchases(int id) {
    User user = userRepo.findById(id);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    return user.getPurchases();
  }

  @Transactional
  public int buyGame(int id, int gameId, int cost) {
    User user = userRepo.findById(id);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    Game game = gameService.getGameById(gameId);
    Purchase purchase = new Purchase(user, game, cost);
    user.buyGame(purchase);
    userRepo.save(user);
    return 0;
  }

  @Transactional
  public List<Game> getUserGamesAuthored(int id) {
    User user = userRepo.findById(id);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    return user.getGamesAuthored();
  }

  @Transactional
  public int addUserGamesAuthored(int id, int gameId) {
    User user = userRepo.findById(id);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    Game game = gameService.getGameById(gameId);
    user.addGameAuthored(game);
    userRepo.save(user);
    return 0;
  }

  @Transactional
  public List<Message> getUserMessages(int id) {
    User user = userRepo.findById(id);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    return user.getMessages();
  }

  @Transactional
  public int addUserMessages(int id, int messageId) {
    User user = userRepo.findById(id);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    Message message = messageService.getMessage(messageId);
    user.addMessage(message);
    userRepo.save(user);
    return 0;
  }
}
