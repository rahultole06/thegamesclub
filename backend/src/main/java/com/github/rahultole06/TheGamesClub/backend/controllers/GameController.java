/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the game table
 * */

package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.GameService;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.io.File;
import java.io.IOException;

public class GameController {

  public final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  public int createGame(String name, String description, User author, String genre, String year, File gameFile) {
    try {
      Game game;
      if (description == null && genre == null) {
        game = new Game(name, author, year, null, null, 0);
      }
      return gameService.createGame(game, gameFile);
    } catch (EntityExistsException | IOException e) {
      return -1;
    }
  }

  public Game getGameById(int id) {
    try {
      return gameService.getGameById(id);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public int updateGame(Game game, File newGameFile) {
    try {
      return gameService.updateGame(game, newGameFile);
    } catch (EntityNotFoundException | IOException e) {
      return -1;
    }
  }

  public int deleteGameById(int id) {
    try {
      return gameService.deleteGameById(id);
    } catch (EntityNotFoundException e) {
      return -1;
    }
  }
}
