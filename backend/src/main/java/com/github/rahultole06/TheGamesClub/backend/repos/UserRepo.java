package com.github.rahultole06.TheGamesClub.backend.repos;

import com.github.rahultole06.TheGamesClub.backend.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
  User findById(int id);
  boolean existsByUsername(String username);
}
