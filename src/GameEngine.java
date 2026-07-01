import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Question> deck;
    private UserInterface ui;
    private int score = 0;
    private int lives = 3;

    public GameEngine(QuestionBank bank, UserInterface ui) {
        this.deck = bank.getRandomQuestions(20);
        this.ui = ui;
    }

    public void startGame() {
        int current = 0;

        while (!deck.isEmpty() && lives > 0) {
            Question q = deck.get(current);

            ui.displayQuestion(q, score, lives, deck.size());
            int userAnswer = ui.getUserAnswer();

            if (userAnswer == q.getCorrectAnswer()) {
                score += 10;
                deck.remove(current);
                ui.showCorrectFeedback();
            } else {
                lives--;
                ui.showWrongFeedback(q);
                current++;
            }

            if (current >= deck.size()) {
                current = 0;
            }
        }

        endGame();
    }

    private void endGame() {
        if (deck.isEmpty()) {
            ui.showWinScreen(score);
        } else {
            ui.showLoseScreen(score);
        }
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}