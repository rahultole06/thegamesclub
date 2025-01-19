/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the user table
 */

package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.UserService;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public int createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping("/users/{id}")
	public int updateUser(@PathVariable int id, String newUsername, String oldPassword, String newPassword) {
		return userService.updateUser(id, newUsername, oldPassword, newPassword);
	}

	@DeleteMapping("/users/{id}")
	public int deleteUser(@PathVariable int id, String password) {
		try {
			return userService.deleteUser(id, password);
		} catch (EntityNotFoundException | IllegalArgumentException e) {
			return -1;
		}
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		try {
			return userService.getUserById(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@GetMapping("/users/{id}/purchases")
	public List<Purchase> getUserPurchases(@PathVariable int id) {
		try {
			return userService.getUserPurchases(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@PostMapping("/users/{id}/purchases/{gameId}")
	public int buyGame(@PathVariable int id, @PathVariable int gameId, int cost) {
		try {
			return userService.buyGame(id, gameId, cost);
		} catch (EntityNotFoundException e) {
			return -1;
		}
	}

	@GetMapping("/users/{id}/authored")
	public List<Game> getUserGamesAuthored(@PathVariable int id) {
		try {
			return userService.getUserGamesAuthored(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@PostMapping("/users/{id}/authored/{gameId}")
	public int addUserGamesAuthored(@PathVariable int id, @PathVariable int gameId) {
		try {
			return userService.addUserGamesAuthored(id, gameId);
		} catch (EntityNotFoundException e) {
			return -1;
		}
	}

	@GetMapping("/users/{id}/messages")
	public List<Message> getUserMessages(@PathVariable int id) {
		try {
			return userService.getUserMessages(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@PostMapping("/users/{id}/messages/{messageId}")
	public int addUserMessages(@PathVariable int id, @PathVariable int messageId) {
		try {
			return userService.addUserMessages(id, messageId);
		} catch (EntityNotFoundException e) {
			return -1;
		}
	}
}
