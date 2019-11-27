package fr.epsi.jconte.service.impl;

import fr.epsi.jconte.Trivia;
import fr.epsi.jconte.model.IPlayer;
import fr.epsi.jconte.model.TypeQuestion;
import fr.epsi.jconte.service.IRollTrivia;
import org.apache.log4j.Logger;

import java.util.List;

public class RollTrivia implements IRollTrivia {

    private Logger logger;

    public RollTrivia(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void roll(int rollNumber, Trivia trivia) {
        logger.info(trivia.getPlayers().get(trivia.getCurrentPlayer()) + " is the current player");
        logger.info("They have rolled a " + rollNumber);

        if (trivia.getPenaltyBox()[trivia.getCurrentPlayer()]) {
            if (rollNumber % 2 != 0) {
                trivia.setGettingOutOfPenaltyBox(true);

                logger.info(trivia.getPlayers().get(trivia.getCurrentPlayer()) + " is getting out of the penalty box");
                trivia.getPlaces()[trivia.getCurrentPlayer()] = trivia.getPlaces()[trivia.getCurrentPlayer()] + rollNumber;
                if (trivia.getPlaces()[trivia.getCurrentPlayer()] > 11) trivia.getPlaces()[trivia.getCurrentPlayer()] = trivia.getPlaces()[trivia.getCurrentPlayer()] - 12;

                printCurrentPlayerLocation(trivia.getPlayers(), trivia.getCurrentPlayer(), trivia.getPlaces());
                printCategory(currentCategory(trivia.getPlaces(), trivia.getCurrentPlayer()));
                askQuestion();
            } else {
                logger.info(players.get(trivia.getCurrentPlayer()) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            trivia.getPlaces()[trivia.getCurrentPlayer()] = trivia.getPlaces()[trivia.getCurrentPlayer()] + roll;
            if (trivia.getPlaces()[trivia.getCurrentPlayer()] > 11) trivia.getPlaces()[trivia.getCurrentPlayer()] = trivia.getPlaces()[trivia.getCurrentPlayer()] - 12;

            logger.info(players.get(trivia.getCurrentPlayer())
                    + "'s new location is "
                    + trivia.getPlaces()[trivia.getCurrentPlayer()]);
            printCategory(currentCategory());
            askQuestion();
        }
    }
    private void printCurrentPlayerLocation(List<IPlayer> players, int currentPlayer, int[] places) {

        logger.info(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
    }
    private void printCategory(TypeQuestion currentCategory) {
        logger.info("The category is " + currentCategory.getTypeQuestion());
    }
    private TypeQuestion currentCategory(int[] places, int currentPlayer) {
        if (places[currentPlayer] == 0) return TypeQuestion.POP;
        if (places[currentPlayer] == 4) return TypeQuestion.POP;
        if (places[currentPlayer] == 8) return TypeQuestion.POP;
        if (places[currentPlayer] == 1) return TypeQuestion.SCIENCE;
        if (places[currentPlayer] == 5) return TypeQuestion.SCIENCE;
        if (places[currentPlayer] == 9) return TypeQuestion.SCIENCE;
        if (places[currentPlayer] == 2) return TypeQuestion.SPORTS;
        if (places[currentPlayer] == 6) return TypeQuestion.SPORTS;
        if (places[currentPlayer] == 10) return TypeQuestion.SPORTS;
        return TypeQuestion.ROCK;
    }
}
