/**
 * Made by: Rahul M. Tole
 * Purpose: Public api for the purchase table
 */

package com.github.rahultole06.TheGamesClub.backend.controllers;

import com.github.rahultole06.TheGamesClub.backend.services.PurchaseService;
import com.github.rahultole06.TheGamesClub.backend.tables.Game;
import com.github.rahultole06.TheGamesClub.backend.tables.Purchase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {

	private final PurchaseService purchaseService;

	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	/**
	 * Create a new purchase
	 * */
	@PostMapping("/purchases")
	public int createPurchase(int buyerId, @RequestBody Game game, int cost) {
		return purchaseService.createPurchase(buyerId, game, cost);
	}

	/**
	 * Returns a purchase
	 * */
	@GetMapping("/purchases/{id}")
	public Purchase getPurchaseById(@PathVariable int id) {
		try {
			return purchaseService.getPurchaseById(id);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}
}
