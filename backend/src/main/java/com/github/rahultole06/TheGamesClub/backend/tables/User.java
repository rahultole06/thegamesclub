/**
 * Made by: Rahul M. Tole
 * Purpose: Store a single user
 * */
package com.github.rahultole06.TheGamesClub.backend.tables;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  @Column(nullable = false)
    private String username;

  @Column(nullable = false)
    private String password;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> gamesAuthored;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

  public User() {

  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    purchases = new ArrayList<>();
    gamesAuthored = new ArrayList<>();
    messages = new ArrayList<>();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Purchase> getPurchases() {
    return purchases;
  }

  public void buyGame(Purchase purchase) {
    purchases.add(purchase);
  }

  public List<Game> getGamesAuthored() {
    return gamesAuthored;
  }

  public void addGameAuthored(Game game) {
    gamesAuthored.add(game);
  }

  public void removeGameAuthored(Game game) {
    gamesAuthored.remove(game);
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void addMessage(Message message) {
    messages.add(message);
  }
}
