package com.cric.stats;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cric.stats.dao.PlayerRepository;
import com.cric.stats.entities.Match;
import com.cric.stats.entities.Player;
import com.cric.stats.service.PlayerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void testCreatePlayer() {
        // Mock player data
        Player player = new Player(null, "Player 1", LocalDate.of(1990, 1, 1), "Country 1", null);

        // Mock repository behavior
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        // Call the method under test
        Player result = playerService.createPlayer(player);

        // Verify the result
        assertNotNull(result);
        assertEquals("Player 1", result.getName());
    }

    @Test
    public void testUpdatePlayer() {
        // Mock player data
        Player existingPlayer = new Player(1L, "Player 1", LocalDate.of(1990, 1, 1), "Country 1", null);
        Player updatedPlayer = new Player(1L, "Updated Player", LocalDate.of(1995, 1, 1), "Updated Country", null);

        // Mock repository behavior
        when(playerRepository.findById(1L)).thenReturn(Optional.of(existingPlayer));
        when(playerRepository.save(any(Player.class))).thenReturn(updatedPlayer);

        // Call the method under test
        Player result = playerService.updatePlayer(1L, updatedPlayer);

        // Verify the result
        assertNotNull(result);
        assertEquals("Updated Player", result.getName());
    }

    @Test
    public void testDeleteExistingPlayer() {
        // Mock player data
        Player player = new Player(1L, "Test Player", LocalDate.now(), "Test Country", null);

        // Mock repository behavior
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        // Call the method under test
        assertDoesNotThrow(() -> playerService.deletePlayer(1L));

        // Verify that player was deleted
        verify(playerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteNonExistingPlayer() {
        // Mock repository behavior for player not found
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method under test and verify it throws an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> playerService.deletePlayer(1L));

        // Verify the exception message
        assertEquals("Player not found with ID: 1", exception.getMessage());

        // Verify that deleteById method is not called
        verify(playerRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testGetPlayerById() {
        // Mock player data
        Player player = new Player(1L, "Player 1", LocalDate.of(1990, 1, 1), "Country 1", null);

        // Mock repository behavior
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        // Call the method under test
        Player result = playerService.getPlayerById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals("Player 1", result.getName());
    }

    @Test
    public void testGetPlayerListByAverageScore() {
        // Mock player data with associated matches
        Player player1 = new Player(1L, "Player 1", LocalDate.of(1990, 1, 1), "Country 1", 
                Arrays.asList(new Match(1L, 85, "Stadium 1"), new Match(2L, 90, "Stadium 2")));
        Player player2 = new Player(2L, "Player 2", LocalDate.of(1995, 1, 1), "Country 2", 
                Arrays.asList(new Match(3L, 95, "Stadium 3"), new Match(4L, 90, "Stadium 4")));
        Player player3 = new Player(3L, "Player 3", LocalDate.of(1985, 1, 1), "Country 3", 
                Arrays.asList(new Match(5L, 100, "Stadium 5"), new Match(6L, 95, "Stadium 6")));

        // Print mock player data for debugging
        System.out.println(Arrays.asList(player1, player2, player3));

        // Mock repository behavior
        System.out.println(playerRepository); // Debugging statement
        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2, player3));

        // Call the method under test
        List<Player> result = playerService.getPlayerListByAverageScore(90);

     // Debugging statement to inspect the actual contents of the result list
        System.out.println("Actual Result: " + result);
        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Player 3", result.get(0).getName()); // Player 3 has the highest average score
        assertEquals("Player 2", result.get(1).getName()); // Player 2 has the second highest average score
    }


    @Test
    public void testGetPlayerListByCountry() {
        // Mock player data
        Player player1 = new Player(1L, "Player 1", LocalDate.of(1990, 1, 1), "Country 1", null);
        Player player2 = new Player(2L, "Player 2", LocalDate.of(1995, 1, 1), "Country 2", null);
        Player player3 = new Player(3L, "Player 3", LocalDate.of(1985, 1, 1), "Country 1", null);

        // Mock repository behavior
        when(playerRepository.findByCountry("Country 1")).thenReturn(Arrays.asList(player1, player3));

        // Call the method under test
        List<Player> result = playerService.getPlayerListByCountry("Country 1");

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(player1));
        assertTrue(result.contains(player3));
    }
}
