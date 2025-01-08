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

  /**
   * Public api to create a game
   * */
  public int createGame(String name, String description, User author, String genre, String year, File gameFile) {
    if (name == null || author == null || year == null || gameFile == null) { return -1; }
    try {
      Game game;

      /*
       * This accounts for optional values
       */
      if (description == null && genre == null) {
        game = new Game(name, author, year, null, null, 0);
      } else if (description == null) {
        game = new Game(name, author, genre, year, null, null, 0);
      } else if (genre == null) {
        game = new Game(name, description, author, year, null, null, 0);
      } else {
        game = new Game(name, description, author, genre, year, null, null, 0);
      }

      return gameService.createGame(game, gameFile);
    } catch (EntityExistsException | IOException e) {
      return -1;
    }
  }

  /**
   * Sends game file for downloading
   * */
  public File downloadGameFile(int gameId) {
    try {
      return gameService.downloadGameFile(gameId);
    } catch (EntityNotFoundException | IOException e) {
      return null;
    }
  }

  /**
   * Returns game for accessing info
   * */
  public Game getGameById(int id) {
    try {
      return gameService.getGameById(id);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  /**
   * Updates game information
   * */
  public int updateGame(String newName, String newDesc, String newGenre, File newGameFile) {
    try {
      Game updateGame = new Game(newName, newDesc, null, newGenre, null, null, 0);
      return gameService.updateGame(updateGame, newGameFile);
    } catch (EntityNotFoundException | IOException e) {
      return -1;
    }
  }


  /**
   * Delete a game
   * */
  public int deleteGameById(int id) {
    try {
      return gameService.deleteGameById(id);
    } catch (EntityNotFoundException e) {
      return -1;
    }
  }
}
