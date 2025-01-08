package com.github.rahultole06.TheGamesClub.backend.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

  public String saveFile(File file, int gameId) throws IOException {
    final String storageDir = "game-files/";
    String uploadDir = storageDir + gameId;
    Path uploadPath = Paths.get(uploadDir);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    String fileName = file.getName();
    Path filePath = uploadPath.resolve(fileName);
    Files.copy(file.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);

    return filePath.toString(); // Return the file path for saving in the database
  }

  public File loadFile(String filePath) {
    return new File(filePath);
  }

  public void deleteFile(String filePath) throws IOException {
    Files.deleteIfExists(Paths.get(filePath));
  }
}
