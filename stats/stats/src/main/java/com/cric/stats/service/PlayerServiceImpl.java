package com.cric.stats.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cric.stats.dao.PlayerRepository;
import com.cric.stats.entities.Match;
import com.cric.stats.entities.Player;

@Service
public class PlayerServiceImpl implements PlayerService {

	
	 @Autowired
	    private PlayerRepository playerRepository;
	 
	@Override
	public Player createPlayer(Player player) {
		 if (player.getName() == null || player.getName().isEmpty()) {
	            throw new IllegalArgumentException("Player name cannot be empty");
	        }
	        // Add additional validation/business logic as needed

		 
	        // Save the player entity
	        return playerRepository.save(player);
	}

	@Override
	public Player updatePlayer(Long playerId, Player player) {
		// TODO Auto-generated method stub
		// Check if the player with the given ID exists
        Optional<Player> existingPlayerOptional = playerRepository.findById(playerId);
        if (existingPlayerOptional.isPresent()) {
            Player existingPlayer = existingPlayerOptional.get();
            // Update the fields of the existing player with the new values
            existingPlayer.setName(player.getName());
            existingPlayer.setDateOfBirth(player.getDateOfBirth());
            existingPlayer.setCountry(player.getCountry());
            // You may also update other fields if needed

            // Save the updated player using the repository
            return playerRepository.save(existingPlayer);
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
	}

	@Override
	public void deletePlayer(Long playerId) {
		// TODO Auto-generated method stub
		// Check if the player with the given ID exists
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isPresent()) {
            // Delete the player if found
            playerRepository.deleteById(playerId);
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }

	}

	@Override
	public Player getPlayerById(Long playerId) {
		// TODO Auto-generated method stub
		// Retrieve the player by ID from the repository
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isPresent()) {
            return playerOptional.get();
        } else {
            throw new IllegalArgumentException("Player not found with ID: " + playerId);
        }
	}

	@Override
	public List<Player> getPlayerListByAverageScore(int averageScore) {
		// TODO Auto-generated method stub
		List<Player> players = playerRepository.findAll();

	    // Filter players whose average score is more than the specified averageScore
	    List<Player> filteredPlayers = players.stream()
	            .filter(player -> {
	                double totalScore = 0;
	                int matchCount = 0;
	                for (Match match : player.getMatches()) {
	                    totalScore += match.getScore();
	                    matchCount++;
	                }
	                double average = matchCount > 0 ? totalScore / matchCount : 0;
	                return average > averageScore;
	            })
	            .sorted((p1, p2) -> {
	                // Sort by average score (descending)
	                double avgScore1 = p1.getMatches().stream().mapToInt(Match::getScore).average().orElse(0);
	                double avgScore2 = p2.getMatches().stream().mapToInt(Match::getScore).average().orElse(0);
	                if (avgScore1 != avgScore2) {
	                    // If average scores are different, sort by average score (descending)
	                    return Double.compare(avgScore2, avgScore1);
	                } else {
	                    // If average scores are equal, sort by age (ascending)
	                    return p1.getDateOfBirth().compareTo(p2.getDateOfBirth());
	                }
	            })
	            .collect(Collectors.toList());

	    return filteredPlayers;
	}

	@Override
	public List<Player> getPlayerListByCountry(String country) {
		// TODO Auto-generated method stub
		return playerRepository.findByCountry(country);
	}

}
