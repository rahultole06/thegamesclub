package com.github.rahultole06.TheGamesClub.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

	/**
	 * Saves game file
	 */
	public String saveFile(MultipartFile file, int gameId) throws IOException {
		// holds the game store
		final String storageDir = "game-files/";
		String uploadDir = storageDir + gameId;
		Path uploadPath = Paths.get(uploadDir);

		// Create directory if doesn't exist
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		// Stores game file in directory
		String fileName = file.getOriginalFilename();
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return filePath.toString(); // Return the file path for saving in the database
	}

	/**
	 * Returns game file
	 */
	public byte[] loadFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		return Files.readAllBytes(path); // Read file content as byte array
	}

	/**
	 * Deletes game file
	 */
	public void deleteFile(String filePath) throws IOException {
		Files.deleteIfExists(Paths.get(filePath));
	}
}
