/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the games table
 * */

package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.GameRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GameService {

  private final GameRepo gameRepo;
  private final FileService fileService;

  public GameService(GameRepo gameRepo, FileService fileService) {
    this.gameRepo = gameRepo;
    this.fileService = fileService;
  }

  @Transactional
  public int createGame(Game game, File file) throws IOException {
    if (gameRepo.existsById(game.getId())) {
      throw new EntityExistsException("Game already exists");
    }
    // Save file to local storage
    String filePath = fileService.saveFile(file, game.getId());
    game.setFileName(file.getName());
    game.setFilePath(filePath);
    game.setFileSize(file.length());

    gameRepo.save(game);
    return 0;
  }

  public File downloadGameFile(int gameId) throws IOException {
    try {
      Game game = gameRepo.findById(gameId);
      return fileService.loadFile(game.getFilePath());
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException("Game not found");
    }
  }

  @Transactional
  public Game getGameById(int id) {
    if (!gameRepo.existsById(id)) {
      throw new EntityNotFoundException("Game not found");
    }
    return gameRepo.findById(id);
  }

  @Transactional
  public int updateGame(Game game, File newGameFile) throws IOException {
    Game existingGame = gameRepo.findById(game.getId());
    if (existingGame == null) {
      throw new EntityNotFoundException("Game not found");
    }

    // Change current game's values based on input
    existingGame.setGameName(game.getGameName() != null ? game.getGameName() : existingGame.getGameName());
    existingGame.setDescription(game.getDescription() != null ? game.getDescription() : existingGame.getDescription());
    existingGame.setGenre(game.getGenre() != null ? game.getGenre() : existingGame.getGenre());

    // Check if a new file is provided
    if (newGameFile != null) {
      // Delete the old file
      fileService.deleteFile(existingGame.getFilePath());

      // Save the new file
      String newFilePath = fileService.saveFile(newGameFile, existingGame.getId());
      existingGame.setFileName(newGameFile.getName());
      existingGame.setFilePath(newFilePath);
      existingGame.setFileSize(newGameFile.length());
    }

    gameRepo.save(existingGame);
    return 0;
  }

  @Transactional
  public int deleteGameById(int id) {
    if (!gameRepo.existsById(id)) {
      throw new EntityNotFoundException("Game not found");
    }
    gameRepo.deleteById(id);
    return 0;
  }
}
