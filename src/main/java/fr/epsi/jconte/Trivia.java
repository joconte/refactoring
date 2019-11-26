package fr.epsi.jconte;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.log4j.Logger;

public class Trivia {

	private static Logger logger = Logger.getLogger(Trivia.class);

	private static final String pop = "Pop";
	private static final String science = "Science";
	private static final String sports = "Sports";
	private static final String rock = "Rock";

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
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

	    logger.info(playerName + " was added");
	    logger.info("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
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
		if (currentCategory().equals(pop))
			logger.info(popQuestions.removeFirst());
		if (currentCategory().equals(science))
			logger.info(scienceQuestions.removeFirst());
		if (currentCategory().equals(sports))
			logger.info(sportsQuestions.removeFirst());
		if (currentCategory().equals(rock))
			logger.info(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return pop;
		if (places[currentPlayer] == 4) return pop;
		if (places[currentPlayer] == 8) return pop;
		if (places[currentPlayer] == 1) return science;
		if (places[currentPlayer] == 5) return science;
		if (places[currentPlayer] == 9) return science;
		if (places[currentPlayer] == 2) return sports;
		if (places[currentPlayer] == 6) return sports;
		if (places[currentPlayer] == 10) return sports;
		return rock;
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
		
			logger.info("Answer was corrent!!!!");
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
