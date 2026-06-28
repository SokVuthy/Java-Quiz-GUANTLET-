import java.util.ArrayList;


public class GameEngine {

    private QuestionBank questionBank;

    private UserInterface ui;
    private ArrayList<Question> activeQuestions;
    private int score;
    private int totalQuestions;
    private int answeredCorrectly;
    private boolean gameActive;
    
    public GameEngine(QuestionBank questionBank, UserInterface ui) {
        this.questionBank = questionBank;
        this.ui = ui;
        this.activeQuestions = new ArrayList<>();
        this.score = 0;
        this.totalQuestions = 0;
        this.answeredCorrectly = 0;
        this.gameActive = false;
    }
    

}
