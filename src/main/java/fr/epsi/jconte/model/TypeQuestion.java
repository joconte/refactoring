package fr.epsi.jconte.model;

public enum TypeQuestion {

    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock"),
    ;

    private String typeQuestion;

    TypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public String getTypeQuestion() {
        return this.typeQuestion;
    }
}
