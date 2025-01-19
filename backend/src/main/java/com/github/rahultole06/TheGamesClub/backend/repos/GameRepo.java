package com.github.rahultole06.TheGamesClub.backend.repos;

import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepo extends JpaRepository<Game, Integer> {
  Game findById(int id);
  boolean existsById(int id);
  void deleteById(int id);

  @Query(value = "SELECT id FROM games ORDER BY id DESC LIMIT 1", nativeQuery = true)
  Integer findLatestGameId();
}
