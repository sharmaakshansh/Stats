package com.cric.stats.service;

import java.util.List;

import com.cric.stats.entities.Player;

public interface PlayerService {

	public Player createPlayer(Player player);
	public Player updatePlayer(Long playerId, Player player);
	public void deletePlayer(Long playerId);
	 public Player getPlayerById(Long playerId);
	 public List<Player> getPlayerListByAverageScore(int averageScore);
	 public List<Player> getPlayerListByCountry(String country);
}
