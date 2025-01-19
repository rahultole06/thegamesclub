/**
 * Made by: Rahul M. Tole
 * Purpose: Holds a purchase made by a user
 */
package com.github.rahultole06.TheGamesClub.backend.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

	@ManyToOne
		private User user;

	@OneToOne
		private Game game;

	@Column(nullable = false)
		private int cost;

	public Purchase() {

	}

	public Purchase(User user, Game game, int cost) {
		this.user = user;
		this.game = game;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
