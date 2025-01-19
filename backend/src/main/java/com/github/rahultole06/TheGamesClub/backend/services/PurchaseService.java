/**
 * Made by: Rahul M. Tole
 * Purpose: Handles logic performed on the purchases table
 */

package com.github.rahultole06.TheGamesClub.backend.services;

import com.github.rahultole06.TheGamesClub.backend.repos.PurchaseRepo;
import com.github.rahultole06.TheGamesClub.backend.repos.UserRepo;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import com.github.rahultole06.TheGamesClub.backend.tables.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

	private final PurchaseRepo purchaseRepo;
	private final UserRepo userRepo;

	public PurchaseService(PurchaseRepo purchaseRepo, UserRepo userRepo) {
		this.purchaseRepo = purchaseRepo;
		this.userRepo = userRepo;
	}

	@Transactional
	public int createPurchase(int buyerId, Game game, int cost) {
		User buyer = userRepo.findById(buyerId);
		Purchase purchase = new Purchase(buyer, game, cost);
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
