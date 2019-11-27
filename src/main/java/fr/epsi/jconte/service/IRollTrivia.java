package fr.epsi.jconte.service;

import fr.epsi.jconte.Trivia;
import org.apache.log4j.Logger;

public interface IRollTrivia {

    void roll(int rollNumber, Trivia trivia);
}
