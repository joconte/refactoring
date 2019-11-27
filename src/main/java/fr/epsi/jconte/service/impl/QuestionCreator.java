package fr.epsi.jconte.service.impl;

import fr.epsi.jconte.model.IQuestion;
import fr.epsi.jconte.model.TypeQuestion;
import fr.epsi.jconte.model.impl.Question;
import fr.epsi.jconte.service.IQuestionCreator;

import java.util.ArrayList;
import java.util.List;

public class QuestionCreator implements IQuestionCreator {
    @Override
    public List<IQuestion> createQuestionsByType(TypeQuestion typeQuestion, int nbOfQuestion) {
        List<IQuestion> questions = new ArrayList<>();
        for (int i = 0; i < nbOfQuestion; i++) {
            questions.add(new Question(typeQuestion, i + 1, typeQuestion.toString() + " question " + i + 1));
        }
        return questions;
    }
}
