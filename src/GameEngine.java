import java.util.ArrayList;
public class GameEngine {
    private ArrayList<Questions> deck;
    private UserInterFace ui;
    private int score = 0;

    public GameEngine(QuestionBank bank, UserInterFace ui) {
        this.deck = getRandomQuestion(20);
        this.ui = ui;
    }

    public void startGame() {
        int current = 0;
        while (!deck.isEmpty()) {
            Question q = deck.get(current);

            ui.displayQuestion(q, score, deck.size());
            int userAnswer = ui.getUserAnswer();

            if (userAnswer == q.getCorrectAnswer()) {
                score += 10;
                deck.remove(current);
                ui.showCorrectFeedback();
            }
            else {
                ui.showWrongFeedback(q);
                current = 0;
            }

            endGame();
        }

        private void endGame() {
            if (deck.isEmpty()) {
                ui.showWinScreen(score);
            }
            else {
                ui.showLoseScreen(score);
            }
        }

        public int getScore() {
            return score;
        }
    }
}
