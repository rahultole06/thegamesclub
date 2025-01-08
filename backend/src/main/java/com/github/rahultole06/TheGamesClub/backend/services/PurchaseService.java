/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the purchases table
 * */

package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.PurchaseRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  private final PurchaseRepo purchaseRepo;

  public PurchaseService(PurchaseRepo purchaseRepo) {
    this.purchaseRepo = purchaseRepo;
  }

  @Transactional
  public int createPurchase(Purchase purchase) {
    if (purchaseRepo.existsById(purchase.getId())) {
      throw new EntityExistsException("Purchase already exists");
    }
    purchaseRepo.save(purchase);
    return 0;
  }

  @Transactional
  public Purchase getPurchaseById(int id) {
    if (!purchaseRepo.existsById(id)) {
      throw new EntityNotFoundException("Purchase not found");
    }
    return purchaseRepo.findById(id);
  }
}
