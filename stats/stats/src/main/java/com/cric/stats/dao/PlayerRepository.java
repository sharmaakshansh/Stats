package com.cric.stats.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cric.stats.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByCountry(String country);

}
