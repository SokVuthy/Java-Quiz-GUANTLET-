import java.util.ArrayList;

public class GameEngine {
    private final ArrayList<Question> deck;
    private final UserInterface ui;
    private int score = 0;
    private int lives = 3;
    private final int totalQuestions;
    private int correctCount = 0;

    public GameEngine(QuestionBank bank, UserInterface ui, String category) {
        if (category == null || category.isBlank()) {
            this.deck = bank.getRandomQuestions(20);
        } else {
            this.deck = bank.getRandomQuestionsByCategory(category, 20);
        }
        this.totalQuestions = this.deck.size();
        this.ui = ui;
        ui.setGameEngine(this);
    }

    public void startGame() {
        int current = 0;

        while (!deck.isEmpty() && lives > 0) {
            Question q = deck.get(current);

            ui.displayQuestion(q, current + 1, deck.size(), score, correctCount,totalQuestions);
            char userAnswer = ui.getAnswerInput();

            if (userAnswer == 'Q') {
                break;
            }

            if (userAnswer == q.getCorrectAnswer()) {
                score += 10;
                correctCount++;
                deck.remove(current);
                ui.displayAnswerFeedback(true, q);
            } else {
                lives--;
                ui.displayAnswerFeedback(false, q);
                current++;
            }

            // ui.displayProgress(correctCount, totalQuestions);

            if (current >= deck.size()) {
                current = 0;
            }
        }

        endGame();
    }

    private void endGame() {
        if (deck.isEmpty()) {
            ui.displayWinScreen(score, totalQuestions);
        } 
        else {
            ui.displayGameOverScreen(score, correctCount, totalQuestions);
            
        }
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}