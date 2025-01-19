/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the game table
 */

package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.GameService;
import com.github.rahultole06.TheGamesClub.backend.services.UserService;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class GameController {

	private final GameService gameService;
	private final UserService userService;

	public GameController(GameService gameService, UserService userService) {
		this.gameService = gameService;
		this.userService = userService;
	}

	/**
	 * Public api to create a game
	 * */
	@PostMapping("/games")
	public int createGame(String name, String description, int authorId, String genre, String year, MultipartFile gameFile) {
		try {
			Game game;
			User author = userService.getUserById(authorId);

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
	@GetMapping("/games/{id}/file")
	public byte[] downloadGameFile(@PathVariable int id) {
		try {
			return gameService.downloadGameFile(id);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Returns game for accessing info
	 * */
	@GetMapping("/games/{id}")
	public Game getGameById(@PathVariable int id) {
		try {
			return gameService.getGameById(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	/**
	 * Updates game information
	 * */
	@PutMapping("/games/{id}")
	public int updateGame(@PathVariable int id, String newName, String newDesc, String newGenre, MultipartFile newGameFile) {
		try {
			Game updateGame = new Game(newName, newDesc, null, newGenre, null, null, 0);
			return gameService.updateGame(id, updateGame, newGameFile);
		} catch (EntityNotFoundException | IOException e) {
			return -1;
		}
	}


	/**
	 * Delete a game
	 * */
	@DeleteMapping("/games/{id}")
	public int deleteGameById(@PathVariable int id) {
		try {
			return gameService.deleteGameById(id);
		} catch (EntityNotFoundException e) {
			return -1;
		}
	}
}
