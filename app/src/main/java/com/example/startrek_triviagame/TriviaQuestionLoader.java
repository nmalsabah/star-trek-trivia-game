package com.example.startrek_triviagame;

public class TriviaQuestionLoader {

    private final StarTrekGameDao starTrekGameDao;

    public TriviaQuestionLoader(StarTrekGameDao starTrekGameDao) {
        this.starTrekGameDao = starTrekGameDao;
    }

    public void loadTriviaQuestions() {
        if (starTrekGameDao.getAllTriviaQuestions().isEmpty()) {
            TriviaQuestions question1 = new TriviaQuestions(
                    "What is Picard's favorite beverage to order from the replicator?",
                    "Hard",
                    "Coffee, Black",
                    "Tea, Earl Grey, Hot",
                    "Raktajino",
                    "Romulan Ale"
            );
            starTrekGameDao.insertTriviaQuestion(question1);

            TriviaQuestions question2 = new TriviaQuestions(
                    "What is the Klingon homeworld called?",
                    "Hard",
                    "Cardassia Prime",
                    "Kronos",
                    "Qo'noS",
                    "Romulus"
            );
            starTrekGameDao.insertTriviaQuestion(question2);

            TriviaQuestions question3 = new TriviaQuestions(
                    "What is the name of the fictional substance that powers Federation starships?",
                    "Medium",
                    "Dilithium",
                    "Tritanium",
                    "Unobtanium",
                    "Quantumium"
            );
            starTrekGameDao.insertTriviaQuestion(question3);

            TriviaQuestions question4 = new TriviaQuestions(
                    "What is the iconic phrase that Borgs often say when assimilating other species?",
                    "Easy",
                    "We are the Collective",
                    "We are the Borg",
                    "You will be assimilated",
                    "Resistance is futile"
            );
            starTrekGameDao.insertTriviaQuestion(question4);

            TriviaQuestions question5 = new TriviaQuestions(
                    "What is the currency used by Ferengis, often referred to as the currency of the galaxy?",
                    "Medium",
                    "Credits",
                    "Gold Pressed Latinium",
                    "Quatloos",
                    "Zek Dollars"
            );
            starTrekGameDao.insertTriviaQuestion(question5);
        }
    }
}
