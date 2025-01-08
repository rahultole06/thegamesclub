package com.github.rahultole06.TheGamesClub.backend;

import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import com.github.rahultole06.TheGamesClub.backend.repos.GameRepo;
import com.github.rahultole06.TheGamesClub.backend.repos.MessageRepo;
import com.github.rahultole06.TheGamesClub.backend.repos.PurchaseRepo;
import com.github.rahultole06.TheGamesClub.backend.repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final UserRepo userRepo;
  private final PurchaseRepo purchaseRepo;
  private final GameRepo gameRepo;
  private final MessageRepo messageRepo;

  public Runner(UserRepo repository, PurchaseRepo purchaseRepo, GameRepo gameRepo, MessageRepo messageRepo) {
    this.userRepo = repository;
    this.purchaseRepo = purchaseRepo;
    this.gameRepo = gameRepo;
    this.messageRepo = messageRepo;
  }

  @Override
  public void run(String... args) throws Exception {
  }

}
