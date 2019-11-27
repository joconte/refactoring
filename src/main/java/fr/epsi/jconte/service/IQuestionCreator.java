package fr.epsi.jconte.service;

import fr.epsi.jconte.model.IQuestion;
import fr.epsi.jconte.model.TypeQuestion;

import java.util.List;

public interface IQuestionCreator {

    List<IQuestion> createQuestionsByType(TypeQuestion typeQuestion, int nbOfQuestion);
}
