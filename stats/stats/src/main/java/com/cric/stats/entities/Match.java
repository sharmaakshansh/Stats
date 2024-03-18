package com.cric.stats.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "matches") // Specify a custom table name
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private String stadium;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	/**
	 * 
	 */
	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param score
	 * @param stadium
	 */
	public Match(Long id, int score, String stadium) {
		this.id = id;
		this.score = score;
		this.stadium = stadium;
	}

    // Constructors, getters, and setters
}
