package com.github.rahultole06.TheGamesClub.backend.repos;

import com.github.rahultole06.TheGamesClub.backend.tables.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {
  Message findById(int id);
  boolean existsById(int id);
  void deleteById(int id);
}
