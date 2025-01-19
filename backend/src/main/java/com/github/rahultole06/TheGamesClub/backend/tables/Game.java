/**
 * Made by: Rahul M. Tole
 * Purpose: Holds a game listed in the library
 */
package com.github.rahultole06.TheGamesClub.backend.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

	/**
	 * Game description variables
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	@Column(nullable = false)
	  private String gameName;
	  private String description; // optional
	@ManyToOne(fetch = FetchType.LAZY)
	  private User author;
	  private String genre; // optional
	@Column(nullable = false)
	  private String year;

	/**
	 * Game file metadata
	 * */
	@Column(nullable = false)
	  private String fileName;
	@Column(nullable = false)
	  private String filePath;
	@Column(nullable = false)
	  private long fileSize;

	/**
	 * All variables
	 * */
	public Game(String gameName, String description, User author, String genre, String year, String fileName, String filePath, long fileSize) {
		this.gameName = gameName;
		this.description = description;
		this.author = author;
		this.genre = genre;
		this.year = year;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	/**
	 * Only genre (optional)
	 * */
	public Game(String gameName, User author, String genre, String year, String fileName, String filePath, long fileSize) {
		this.gameName = gameName;
		this.author = author;
		this.genre = genre;
		this.year = year;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	/**
	 * Only description (optional)
	 * */
	public Game(String gameName, String description, User author, String year, String fileName, String filePath, long fileSize) {
		this.gameName = gameName;
		this.description = description;
		this.author = author;
		this.year = year;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	/**
	 * Only mandatory variables
	 * */
	public Game(String gameName, User author, String year, String fileName, String filePath, long fileSize) {
		this.gameName = gameName;
		this.author = author;
		this.year = year;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public Game() {

	}

	public int getId() {
		return id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getDescription() {
		if (this.description == null) {
			return "";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getGenre() {
		if (this.genre == null) {
			return "";
		}
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}

