package fr.epsi.jconte;

import fr.epsi.jconte.model.IPlayer;
import fr.epsi.jconte.model.IQuestion;
import fr.epsi.jconte.model.TypeQuestion;
import fr.epsi.jconte.model.impl.Player;
import fr.epsi.jconte.service.IInitTrivia;
import fr.epsi.jconte.service.IQuestionCreator;
import fr.epsi.jconte.service.impl.InitTrivia;
import fr.epsi.jconte.service.impl.QuestionCreator;
import org.apache.log4j.BasicConfigurator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        BasicConfigurator.configure();

        IPlayer player1 = new Player("Chet", 1);
        IPlayer player2 = new Player("Pat", 2);
        IPlayer player3 = new Player("Sue", 3);

        List<IPlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        IQuestionCreator questionCreator = new QuestionCreator();

        List<IQuestion> questions = new ArrayList<>();

        List<IQuestion> questionsRock = questionCreator.createQuestionsByType(TypeQuestion.ROCK, 50);
        List<IQuestion> questionsPop = questionCreator.createQuestionsByType(TypeQuestion.POP, 50);
        List<IQuestion> questionsScience = questionCreator.createQuestionsByType(TypeQuestion.SCIENCE, 50);
        List<IQuestion> questionsSports = questionCreator.createQuestionsByType(TypeQuestion.SPORTS, 50);

        questions.addAll(questionsRock);
        questions.addAll(questionsPop);
        questions.addAll(questionsScience);
        questions.addAll(questionsSports);

        int gameSize = 6;

        Trivia trivia = new Trivia(questions, players, gameSize);

        IInitTrivia initTrivia = new InitTrivia();

        initTrivia.initGame(trivia);




        boolean notAWinner;

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
