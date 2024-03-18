package com.cric.stats.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cric.stats.entities.Player;
import com.cric.stats.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class MyController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPlayer(@RequestBody Player player, Principal principal) {
        // Check if the current user is an admin
        if (!isAdmin(principal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can perform this action");
        }

        // Call the service method to create a player
        try {
            Player createdPlayer = playerService.createPlayer(player);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create player");
        }
    }

    @PutMapping("/update/{playerId}")
    public ResponseEntity<Object> updatePlayer(@PathVariable Long playerId, @RequestBody Player player,
            Principal principal) {
        // Check if the current user is an admin
        if (!isAdmin(principal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can perform this action");
        }

        // Call the service method to update the player
        try {
            Player updatedPlayer = playerService.updatePlayer(playerId, player);
            return ResponseEntity.ok(updatedPlayer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update player");
        }
    }

    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<Object> deletePlayer(@PathVariable Long playerId, Principal principal) {
        // Check if the current user is an admin
        if (!isAdmin(principal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can perform this action");
        }

        // Call the service method to delete the player
        try {
            playerService.deletePlayer(playerId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete player");
        }
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Object> getPlayerById(@PathVariable Long playerId, Principal principal) {
        // Call the service method to get the player by ID
    	 if (!isAdmin(principal)) {
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can perform this action");
         }
    	try {
            Player player = playerService.getPlayerById(playerId);
            return ResponseEntity.ok(player);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch player");
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<Object> getPlayerListByCountry(@PathVariable String country, Principal principal) {
        // Call the service method to get the player list by country
    	 if (!isAdmin(principal)) {
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can perform this action");
         }
    	try {
            List<Player> players = playerService.getPlayerListByCountry(country);
            return ResponseEntity.ok(players);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch players by country");
        }
    }

    @GetMapping("/average-score/{averageScore}")
    public ResponseEntity<Object> getPlayerListByAverageScore(@PathVariable int averageScore, Principal principal) {
        // Call the service method to get the player list by average score
    	 if (!isAdmin(principal)) {
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin users can perform this action");
         }
    	try {
            List<Player> players = playerService.getPlayerListByAverageScore(averageScore);
            return ResponseEntity.ok(players);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch players by average score");
        }
    }

    // Method to check if the current user is an admin
    private boolean isAdmin(Principal principal) {
        // Logic to determine if the user is an admin (e.g., checking roles)
        // For simplicity, let's assume any user with "admin" in their name is an admin
        return principal != null && principal.getName().contains("admin");
    }
}

