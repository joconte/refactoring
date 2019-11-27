package fr.epsi.jconte.model.impl;

import fr.epsi.jconte.model.IQuestion;
import fr.epsi.jconte.model.TypeQuestion;

public class Question implements IQuestion {

    private TypeQuestion typeQuestion;

    private int questionNumber;

    private String questionString;

    public Question(TypeQuestion typeQuestion, int questionNumber, String questionString) {
        this.typeQuestion = typeQuestion;
        this.questionNumber = questionNumber;
        this.questionString = questionString;
    }

    @Override
    public TypeQuestion getTypeQuestion() {
        return null;
    }

    @Override
    public int getQuestionNumber() {
        return 0;
    }

    @Override
    public String getQuestionString() {
        return null;
    }
}
