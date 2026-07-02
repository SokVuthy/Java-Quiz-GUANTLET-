import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    // ── Scanner for all user input ────────────────────────────
    private Scanner scanner;

    // ── Constructor ───────────────────────────────────────────
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    // ==========================================================
    //  MENUS
    // ==========================================================

    // Method 1 – Display the main menu
    public void displayMainMenu() {
        printBox(
            "  ╔══════════════════════════════════════╗",
            "  ║     🎮   QUIZ GAUNTLET   🎮           ║",
            "  ║      20-Question Challenge Game       ║",
            "  ╠══════════════════════════════════════╣",
            "  ║  [1]  Play Game                      ║",
            "  ║  [2]  Admin Panel                    ║",
            "  ║  [3]  View Statistics                ║",
            "  ║  [0]  Exit                           ║",
            "  ╚══════════════════════════════════════╝"
        );
        System.out.print("  👉  Enter your choice: ");
    }

    // Method 2 – Display the admin menu
    public void displayAdminMenu() {
        printBox(
            "  ╔══════════════════════════════════════╗",
            "  ║         🔐  ADMIN PANEL              ║",
            "  ╠══════════════════════════════════════╣",
            "  ║  [1]  View All Questions             ║",
            "  ║  [2]  Add New Question               ║",
            "  ║  [3]  Update Question                ║",
            "  ║  [4]  Delete Question                ║",
            "  ║  [5]  View Category Statistics       ║",
            "  ║  [0]  Back to Main Menu              ║",
            "  ╚══════════════════════════════════════╝"
        );
        System.out.print("  👉  Enter your choice: ");
    }

    // Method 3 – Display the category selection menu
    public void displayCategoryMenu() {
        printBox(
            "  ╔══════════════════════════════════════╗",
            "  ║       📂  SELECT CATEGORY            ║",
            "  ╠══════════════════════════════════════╣",
            "  ║  [1]  Mathematics  🔢                ║",
            "  ║  [2]  World Flags  🌍                ║",
            "  ║  [3]  FIFA World Cup  ⚽              ║",
            "  ║  [4]  Mixed (All Categories)         ║",
            "  ║  [0]  Back                           ║",
            "  ╚══════════════════════════════════════╝"
        );
        System.out.print("  👉  Enter your choice: ");
    }

    // Method 4 – Display the user gateway menu
    public void displayUserMenu() {
        printBox(
            "  ╔══════════════════════════════════════╗",
            "  ║          👤  USER MENU               ║",
            "  ╠══════════════════════════════════════╣",
            "  ║  [1]  Start Quiz                     ║",
            "  ║  [2]  How to Play                    ║",
            "  ║  [0]  Back to Main Menu              ║",
            "  ╚══════════════════════════════════════╝"
        );
        System.out.print("  👉  Enter your choice: ");
    }

    // ==========================================================
    //  QUESTION DISPLAY
    // ==========================================================

    // Method 5 – Display a single question during gameplay
    // Uses: q.getQuestionText(), q.getOptionA/B/C/D(), q.getID()
    public void displayQuestion(Question q, int questionNumber, int totalRemaining, int score) {
        System.out.println();
        System.out.println("  ┌──────────────────────────────────────────┐");
        System.out.printf ("  │  Question #%-3d  Remaining: %-3d  Score: %-3d│%n",
                            questionNumber, totalRemaining, score);
        System.out.println("  ├──────────────────────────────────────────┤");

        // Word-wrap question text to fit box width of 42 chars
        String qText = q.getQuestionText();
        if (qText.length() <= 42) {
            System.out.printf("  │  %-42s│%n", qText);
        } else {
            System.out.printf("  │  %-42s│%n", qText.substring(0, 42));
            String rest = qText.substring(42, Math.min(84, qText.length()));
            System.out.printf("  │  %-42s│%n", rest);
        }

        System.out.println("  ├──────────────────────────────────────────┤");

        // Display each option using Putta's individual getters
        System.out.printf("  │  [A]  %-37s│%n", q.getOptionA());
        System.out.printf("  │  [B]  %-37s│%n", q.getOptionB());
        System.out.printf("  │  [C]  %-37s│%n", q.getOptionC());
        System.out.printf("  │  [D]  %-37s│%n", q.getOptionD());

        System.out.println("  └──────────────────────────────────────────┘");
        System.out.print("  👉  Your answer (A/B/C/D  or  Q to quit): ");
    }

    // Method 6 – Show feedback after the player answers
    // Uses: q.getCorrectAnswer() returns char, q.getOption(char) returns full text
    public void displayAnswerFeedback(boolean isCorrect, Question q) {
        System.out.println();
        if (isCorrect) {
            System.out.println("  ╔══════════════════════════════════════╗");
            System.out.println("  ║  ✅  CORRECT!  +10 points  🎉        ║");
            System.out.println("  ║  This question is removed from deck. ║");
            System.out.println("  ╚══════════════════════════════════════╝");
        } else {
            char correctLetter = q.getCorrectAnswer();
            String correctText = q.getOption(correctLetter);
            System.out.println("  ╔══════════════════════════════════════╗");
            System.out.println("  ║  ❌  WRONG!  No points deducted.    ║");
            System.out.printf ("  ║  Correct: [%c] %-23s║%n",
                               correctLetter, correctText);
            System.out.println("  ║  This question will reappear later. ║");
            System.out.println("  ╚══════════════════════════════════════╝");
        }
        pauseForUser();
    }

    // Method 7 – Display a live progress bar
    public void displayProgress(int correct, int total) {
        int filled = (total > 0) ? (int)((correct / (double) total) * 20) : 0;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 20; i++) bar.append(i < filled ? "█" : "░");
        bar.append("]");
        System.out.println();
        System.out.printf("  📊  Progress: %s  %d / %d correct%n",
                           bar.toString(), correct, total);
    }

    // ==========================================================
    //  WIN / GAME OVER SCREENS
    // ==========================================================

    // Method 8 – Display the win screen when all 20 are answered correctly
    public void displayWinScreen(int score, int totalQuestions) {
        System.out.println();
        System.out.println("  ╔════════════════════════════════════════════╗");
        System.out.println("  ║                                            ║");
        System.out.println("  ║   🏆   CONGRATULATIONS!  YOU WON!   🏆    ║");
        System.out.println("  ║                                            ║");
        System.out.println("  ╠════════════════════════════════════════════╣");
        System.out.printf ("  ║   ✅  All %2d questions answered correctly  ║%n", totalQuestions);
        System.out.printf ("  ║   🌟  Final Score : %-5d / %-5d points   ║%n",
                            score, totalQuestions * 10);
        System.out.println("  ║                                            ║");
        System.out.println("  ╚════════════════════════════════════════════╝");
        pauseForUser();
    }

    // Method 9 – Display the game over / quit screen
    public void displayGameOverScreen(int score, int correctCount, int totalQuestions) {
        System.out.println();
        System.out.println("  ╔════════════════════════════════════════════╗");
        System.out.println("  ║                                            ║");
        System.out.println("  ║           💀   GAME OVER   💀              ║");
        System.out.println("  ║                                            ║");
        System.out.println("  ╠════════════════════════════════════════════╣");
        System.out.printf ("  ║   ✅  Correct answers  : %-3d               ║%n", correctCount);
        System.out.printf ("  ║   ❌  Still remaining  : %-3d               ║%n",
                            totalQuestions - correctCount);
        System.out.printf ("  ║   🌟  Final Score      : %-5d points      ║%n", score);
        System.out.println("  ║                                            ║");
        System.out.println("  ╚════════════════════════════════════════════╝");
        pauseForUser();
    }

    // ==========================================================
    //  STATISTICS & ADMIN VIEWS
    // ==========================================================

    // Method 10 – Display category statistics in a formatted table
    public void displayStatisticsTable(String[] categories, int[] questionCounts) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║         📊  QUESTION BANK STATISTICS         ║");
        System.out.println("  ╠═══════════════════════╦══════════════════════╣");
        System.out.println("  ║  Category             ║  Questions Available ║");
        System.out.println("  ╠═══════════════════════╬══════════════════════╣");

        // Loop through each category (for loop requirement)
        int total = 0;
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("  ║  %-21s║  %-20d║%n",
                              categories[i], questionCounts[i]);
            total += questionCounts[i];
        }

        System.out.println("  ╠═══════════════════════╬══════════════════════╣");
        System.out.printf ("  ║  %-21s║  %-20d║%n", "TOTAL", total);
        System.out.println("  ╚═══════════════════════╩══════════════════════╝");
        pauseForUser();
    }

    // Method 11 – Display all questions in a table (Admin READ view)
    // Uses: q.getID(), q.getQuestionText(), q.getCategories(), q.getCorrectAnswer()
    public void displayAllQuestions(ArrayList<Question> questions) {
        if (questions.isEmpty()) {
            displayMessage("No questions found in the bank.");
            return;
        }

        System.out.println();
        System.out.printf("  %-5s %-38s %-16s %-6s%n",
                          "ID", "Question", "Category", "Answer");
        System.out.println("  " + "─".repeat(68));

        // Loop through all questions (READ operation)
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String qText = q.getQuestionText();
            if (qText.length() > 36) qText = qText.substring(0, 33) + "...";
            System.out.printf("  %-5d %-38s %-16s %-6c%n",
                              q.getID(),
                              qText,
                              q.getCategories(),
                              q.getCorrectAnswer());
        }

        System.out.println("  " + "─".repeat(68));
        System.out.printf("  Total: %d question(s) in the bank.%n%n", questions.size());
        pauseForUser();
    }

    // ==========================================================
    //  INPUT METHODS
    // ==========================================================

    // Method 12 – Get an integer menu choice safely
    public int getIntInput(String prompt) {
        while (true) {
            System.out.print("  " + prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                displayError("Please enter a valid number.");
            }
        }
    }

    // Method 13 – Get answer input A/B/C/D or Q to quit
    // Returns char to match Question's correctAnswer field type
    public char getAnswerInput() {
        while (true) {
            String raw = scanner.nextLine().trim().toUpperCase();
            if (raw.length() == 1) {
                char c = raw.charAt(0);
                if (c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'Q') {
                    return c;
                }
            }
            displayError("Invalid input. Please enter A, B, C, D, or Q to quit.");
            System.out.print("  👉  Your answer (A/B/C/D  or  Q to quit): ");
        }
    }

    // Method 14 – Get general text input from user
    public String getStringInput(String prompt) {
        System.out.print("  " + prompt);
        return scanner.nextLine().trim();
    }

    // Method 15 – Get a single character input (used when admin sets correct answer)
    public char getCharInput(String prompt) {
        while (true) {
            System.out.print("  " + prompt);
            String raw = scanner.nextLine().trim().toUpperCase();
            if (raw.length() == 1) return raw.charAt(0);
            displayError("Please enter a single character (A, B, C, or D).");
        }
    }

    // Method 16 – Get admin password input
    public String getPasswordInput() {
        System.out.print("  🔑  Enter admin password: ");
        return scanner.nextLine().trim();
    }

    // ==========================================================
    //  UTILITY / HELPER DISPLAY METHODS
    // ==========================================================

    // Method 17 – Display a general informational message
    public void displayMessage(String message) {
        System.out.println("  ℹ️   " + message);
    }

    // Method 18 – Display an error message
    public void displayError(String message) {
        System.out.println("  ⚠️   ERROR: " + message);
    }

    // Method 19 – Display a success message
    public void displaySuccess(String message) {
        System.out.println("  ✅  " + message);
    }

    // Method 20 – Display a visual divider line
    public void displayDivider() {
        System.out.println("  " + "═".repeat(46));
    }

    // Method 21 – Display how-to-play instructions
    public void displayHowToPlay() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║           📖  HOW TO PLAY                ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.println("  ║  1. 20 questions are randomly chosen     ║");
        System.out.println("  ║     from a pool of 100+ questions.       ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  2. Answer by typing A, B, C, or D.     ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  3. ✅ Correct answer:                   ║");
        System.out.println("  ║     → +10 points                         ║");
        System.out.println("  ║     → Question removed from active deck  ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  4. ❌ Wrong answer:                     ║");
        System.out.println("  ║     → No points deducted                 ║");
        System.out.println("  ║     → Question stays & may reappear      ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  5. Win by answering ALL 20 correctly!   ║");
        System.out.println("  ║     Type Q at any time to quit.          ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        pauseForUser();
    }

    // Method 22 – Display welcome banner on app launch
    public void displayWelcomeBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║                                          ║");
        System.out.println("  ║   🎮   WELCOME TO QUIZ GAUNTLET   🎮     ║");
        System.out.println("  ║        20-Question Challenge Game        ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║      ITM 201 – Java Programming I        ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println();
    }

    // Method 23 – Display goodbye message on exit
    public void displayExitMessage() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║  👋  Thanks for playing Quiz Gauntlet!   ║");
        System.out.println("  ║       See you next time!  🌟              ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println();
    }

    // ==========================================================
    //  PRIVATE HELPERS
    // ==========================================================

    // Pause and wait for the user to press Enter before continuing
    private void pauseForUser() {
        System.out.print("\n  Press Enter to continue...");
        scanner.nextLine();
    }

    // Print a pre-built set of box lines
    private void printBox(String... lines) {
        System.out.println();
        for (String line : lines) System.out.println(line);
        System.out.println();
    }
}
