package fr.epsi.jconte;

import java.security.SecureRandom;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final int NB_CELLS = 12;
    private static final int NB_QUESTIONS = 50;
    private static final List<Category> CATEGORIES = asList(Category.POP, Category.SCIENCE, Category.SPORTS, Category.ROCK);
    private static final List<String> PLAYERS = asList("Chet", "Pat", "Sue");

    public static void main(String[] args) {
        Game aGame = new Game(
                System.out,
                new Board(NB_CELLS, CATEGORIES),
                new QuestionDeck(NB_QUESTIONS, CATEGORIES),
                new PlayerList(PLAYERS)
        );

        SecureRandom rand = new SecureRandom();

        boolean notAWinner;
        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }


        } while (notAWinner);

    }
}
