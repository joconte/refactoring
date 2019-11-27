package fr.epsi.jconte;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    
    @Test
    public void should_have_won_when_6_gold_coins() {
        Player player = new Player("Foo");
        player.reward(6);

        assertTrue(player.hasWon());
    }

    @Test
    public void should_not_have_won_when_less_than_6_gold_coins() {
        Player player = new Player("Foo");
        player.reward(5);

        assertFalse(player.hasWon());
    }

}