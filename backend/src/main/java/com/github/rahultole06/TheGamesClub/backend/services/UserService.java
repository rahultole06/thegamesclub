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

  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
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
  public int updateUser(User user, String oldPassword) {
    User existingUser = userRepo.findByUsername(user.getUsername());
    if (existingUser == null) {
      throw new EntityNotFoundException("User not found");
    } else if (!existingUser.getPassword().equals(oldPassword)) {
      throw new IllegalArgumentException("Wrong password");
    }
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    userRepo.save(existingUser);
    return 0;
  }

  @Transactional
  public int deleteUser(String username, String password) {
    if (!userRepo.existsByUsername(username)) {
      throw new EntityNotFoundException("User not found");
    } else if (!userRepo.findByUsername(username).getPassword().equals(password)) {
      throw new IllegalArgumentException("Wrong password");
    }
    userRepo.deleteByUsername(username);
    return 0;
  }

  @Transactional
  public User getUserByUsername(String username) {
    if (!userRepo.existsByUsername(username)) {
      throw new EntityNotFoundException("User not found");
    }
    return userRepo.findByUsername(username);
  }

  @Transactional
  public List<Purchase> getUserPurchases(String username) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    return user.getPurchases();
  }

  @Transactional
  public int buyGame(String username, Purchase purchase) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    user.buyGame(purchase);
    userRepo.save(user);
    return 0;
  }

  @Transactional
  public List<Game> getUserGamesAuthored(String username) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    return user.getGamesAuthored();
  }

  @Transactional
  public int addUserGamesAuthored(String username, Game game) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    user.addGameAuthored(game);
    userRepo.save(user);
    return 0;
  }

  @Transactional
  public List<Message> getUserMessages(String username) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    return user.getMessages();
  }

  @Transactional
  public int addUserMessages(String username, Message message) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new EntityNotFoundException("User not found");
    }
    user.addMessage(message);
    userRepo.save(user);
    return 0;
  }
}
