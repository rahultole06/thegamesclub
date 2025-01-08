package com.github.rahultole06.TheGamesClub.backend.repos;

import com.github.rahultole06.TheGamesClub.backend.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
  User findByUsername(String username);
  boolean existsByUsername(String username);
  void deleteByUsername(String username);
}
