/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the games table
 */

package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.GameRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public int createGame(Game game, MultipartFile file) throws IOException {
		// Save file to local storage
		Integer latestId = gameRepo.findLatestGameId();
		int gameId = latestId != null ? latestId + 1 : 1; // Return 0 if no game exists

		String filePath = fileService.saveFile(file, gameId);
		game.setFileName(file.getName());
		game.setFilePath(filePath);
		game.setFileSize(file.getSize());

		gameRepo.save(game);
		return 0;
	}

	public byte[] downloadGameFile(int gameId) throws IOException {
		Game game = gameRepo.findById(gameId);
		return fileService.loadFile(game.getFilePath());
	}

	@Transactional
	public Game getGameById(int id) {
		if (!gameRepo.existsById(id)) {
			throw new EntityNotFoundException("Game not found");
		}
		return gameRepo.findById(id);
	}

	@Transactional
	public int updateGame(int id, Game updatedGame, MultipartFile newGameFile) throws IOException {
		Game existingGame = gameRepo.findById(id);
		if (existingGame == null) {
			throw new EntityNotFoundException("Game not found");
		}

		// Change current game's values based on input
		existingGame.setGameName(updatedGame.getGameName() != null ? updatedGame.getGameName() : existingGame.getGameName());
		existingGame.setDescription(updatedGame.getDescription() != null ? updatedGame.getDescription() : existingGame.getDescription());
		existingGame.setGenre(updatedGame.getGenre() != null ? updatedGame.getGenre() : existingGame.getGenre());

		// Check if a new file is provided
		if (newGameFile != null) {
			// Delete the old file
			fileService.deleteFile(existingGame.getFilePath());

			// Save the new file
			String newFilePath = fileService.saveFile(newGameFile, existingGame.getId());
			existingGame.setFileName(newGameFile.getName());
			existingGame.setFilePath(newFilePath);
			existingGame.setFileSize(newGameFile.getSize());
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
