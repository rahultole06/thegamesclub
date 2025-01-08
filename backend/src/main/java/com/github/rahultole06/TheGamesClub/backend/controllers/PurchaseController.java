/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the purchase table
 * */

package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.PurchaseService;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

public class PurchaseController {

  private final PurchaseService purchaseService;

  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  /**
   * Create a new purchase
   * */
  public int createPurchase(User user, Game game, int cost) {
    if (user == null || game == null || cost < 0) { return -1; }
    try {
      Purchase purchase = new Purchase(user, game, cost);
      return purchaseService.createPurchase(purchase);
    } catch (EntityExistsException e) {
      return -1;
    }
  }

  /**
   * Returns a purchase
   * */
  public Purchase getPurchaseById(int id) {
    try {
      return purchaseService.getPurchaseById(id);
    } catch (EntityNotFoundException e) {
      return null;
    }
  }
}
