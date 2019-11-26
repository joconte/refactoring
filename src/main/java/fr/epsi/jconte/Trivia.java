package fr.epsi.jconte;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.log4j.Logger;

public class Trivia {

	private static Logger logger = Logger.getLogger(Trivia.class);

	private static final String POP = "Pop";
	private static final String SCIENCE = "Science";
	private static final String SPORTS = "Sports";
	private static final String ROCK = "Rock";

    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Trivia(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[players.size()] = 0;
	    purses[players.size()] = 0;
	    inPenaltyBox[players.size()] = false;

	    logger.info(playerName + " was added");
	    logger.info("They are player number " + players.size());
		return true;
	}
	
	public void roll(int roll) {
		logger.info(players.get(currentPlayer) + " is the current player");
		logger.info("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				logger.info(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

				printCurrentPlayerLocation(currentPlayer);
				printCategory(currentCategory());
				askQuestion();
			} else {
				logger.info(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			logger.info(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			printCategory(currentCategory());
			askQuestion();
		}
		
	}

	private void printCurrentPlayerLocation(int currentPlayer) {

		logger.info(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
	}

	private void printCategory(String currentCategory) {
		logger.info("The category is " + currentCategory);
	}

	private void askQuestion() {
		if (currentCategory().equals(POP))
			logger.info(popQuestions.removeFirst());
		if (currentCategory().equals(SCIENCE))
			logger.info(scienceQuestions.removeFirst());
		if (currentCategory().equals(SPORTS))
			logger.info(sportsQuestions.removeFirst());
		if (currentCategory().equals(ROCK))
			logger.info(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return POP;
		if (places[currentPlayer] == 4) return POP;
		if (places[currentPlayer] == 8) return POP;
		if (places[currentPlayer] == 1) return SCIENCE;
		if (places[currentPlayer] == 5) return SCIENCE;
		if (places[currentPlayer] == 9) return SCIENCE;
		if (places[currentPlayer] == 2) return SPORTS;
		if (places[currentPlayer] == 6) return SPORTS;
		if (places[currentPlayer] == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				logger.info("Answer was correct!!!!");
				purses[currentPlayer]++;

				printPlayerGoldCoin(currentPlayer);

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			logger.info("Answer was correct!!!!");
			purses[currentPlayer]++;

			printPlayerGoldCoin(currentPlayer);

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}

	private void printPlayerGoldCoin(int currentPlayer) {

		logger.info(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	public boolean wrongAnswer(){
		logger.info("Question was incorrectly answered");
		logger.info(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return purses[currentPlayer] != 6;
	}
}
