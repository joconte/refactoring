package fr.epsi.jconte;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{

    private static boolean notAWinner;

    public static void main( String[] args )
    {
        Trivia trivia = new Trivia();

        trivia.add("Chet");
        trivia.add("Pat");
        trivia.add("Sue");

        SecureRandom rand = new SecureRandom();

        do {

            trivia.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = trivia.wrongAnswer();
            } else {
                notAWinner = trivia.wasCorrectlyAnswered();
            }



        } while (notAWinner);
    }
}
