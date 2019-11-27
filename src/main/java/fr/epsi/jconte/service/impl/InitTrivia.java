package fr.epsi.jconte.service.impl;

import fr.epsi.jconte.Trivia;
import fr.epsi.jconte.service.IInitTrivia;

public class InitTrivia implements IInitTrivia {
    @Override
    public void initGame(Trivia trivia) {
        trivia.setPenaltyBox(new boolean[trivia.getGameSize()]);
        trivia.setPlaces(new int[trivia.getGameSize()]);
        trivia.setPurses(new int[trivia.getGameSize()]);

        boolean[] penaltyBox = trivia.getPenaltyBox();
        int[] places = trivia.getPlaces();
        int[] purses = trivia.getPurses();
        for (int i = 0; i <= trivia.getPlayers().size(); i++) {
            penaltyBox[i] = false;
            places[i] = 0;
            purses[i] = 0;
        }
        trivia.setPenaltyBox(penaltyBox);
        trivia.setPurses(purses);
        trivia.setPlaces(places);
    }
}
