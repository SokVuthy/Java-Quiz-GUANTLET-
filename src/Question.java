

public class Question {
    private static int nextId= 1;

    private int id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctAnswer;
    private String categories;

    public Question(String questionText, String optionA, String optionB, String optionC, String optionD, char correctAnswer, String categories){
        this.id = nextId++;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.categories = categories;
    }

    public int getID(){
        return id;
    }

    public String getQuestionText(){
        return questionText;
    }

    public String getOptionA(){
        return optionA;
    }

    public String getOptionB(){
        return optionB;
    }

    public String getOptionC(){
        return optionC;
    }

    public String getOptionD(){
        return optionD;
    }

    public char getCorrectAnswer(){
        return correctAnswer;
    }

    public String getCategories(){
        return categories;
    }

    public String getOption(char letter) {
        switch (letter) {
            case 'A':
                return optionA;
            case 'B':
                return optionB;
            case 'C':
                return optionC;
            case 'D':
                return optionD;
            default:
                return null;
        }
    }

    public boolean isCorrect(char answer){
        return answer == correctAnswer;
    }

    public void display(){
        System.out.println("📝 " + questionText);
        System.out.println("A. " + optionA);
        System.out.println("B. " + optionB);
        System.out.println("C. " + optionC);
        System.out.println("D. " + optionD);
        System.out.println("📂 Category: " + categories);
        System.out.println("🆔 ID: " + id);
    }

    public String toString() {
        return "Question #" + id + " [" + categories + "] " + questionText;
    }


}
