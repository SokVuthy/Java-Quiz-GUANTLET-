
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final String PASSWORD = "admin1213";
    private static boolean isRunning = true;

    private static QuestionBank questionBank;
    private static UserInterface ui;

    public static void main(String[] args) {
        clearScreen();
        initializeApp();
        runningApp();
        System.out.println("Good Bye My Love..... Muah Muah kiss kiss");
    }

    private static void initializeApp() {
        showLoading("Initializing Quiz Gauntlet");
        questionBank = new QuestionBank();
        ui = new UserInterface(input);
        ui.displayWelcomeBanner();
        System.out.println("Ready!!");
    }

    private static void runningApp() {
        System.out.println("Running application..");
        while (isRunning) {
            displayMainMenu();
            switch (getChoice(1, 3)) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Choice must be 1-3");
                    break;
            }
        }
    }

    private static int getChoice(int min, int max) {
        int choice = 0;
        boolean valid = false;

        while (!valid) {
            try {
                if (!input.hasNextInt()) {
                    if (!input.hasNextLine()) {
                        isRunning = false;
                        return min;
                    }
                    input.nextLine();
                    System.out.println("Error: Input must be a number!!!!");
                    continue;
                }

                System.out.print("Choice: ");
                choice = input.nextInt();
                input.nextLine();

                if ((choice >= min) && (choice <= max)) {
                    valid = true;
                } else {
                    System.out.println("Choice must be " + min + "-" + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input must be a number!!!!");
                input.nextLine();
            }
        }
        return choice;
    }

    private static void adminLogin() {
        ui.displayMessage("Log in as Admin");
        String enteredPassword = ui.getPasswordInput();

        if (enteredPassword.equals(PASSWORD)) {
            ui.displaySuccess("Login Successful!");
            adminMenu();
        } else {
            ui.displayError("Incorrect Password!");
        }
    }

    private static void displayMainMenu() {
        showWelcomeBanner();
        System.out.println();
        System.out.println();
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃        ⚔ QUIZ GAUNTLET: THE CHALLENGE ⚔          ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃  ➤ [1] Admin                                     ┃");
        System.out.println("┃  ➤ [2] User                                      ┃");
        System.out.println("┃  ➤ [3] Exit                                      ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    private static void adminMenu() {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                ⚙ ADMIN MENU ⚙                    ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃  ➤ [1] View Questions                            ┃");
        System.out.println("┃  ➤ [2] View Statistics                           ┃");
        System.out.println("┃  ➤ [3] Back                                      ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        int choice = getChoice(1, 3);
        switch (choice) {
            case 1:
                ui.displayAllQuestions(questionBank.getAllQuestions());
                break;
            case 2:
                showStatistics();
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private static void userMenu() {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                 🎮 USER MENU 🎮                  ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃  ➤ [1] Play by Categories                        ┃");
        System.out.println("┃  ➤ [2] Play by Random                            ┃");
        System.out.println("┃  ➤ [3] How to Play                               ┃");
        System.out.println("┃  ➤ [4] View Statistics                           ┃");
        System.out.println("┃  ➤ [5] Back                                      ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        int choice = getChoice(1, 5);
        switch (choice) {
            case 1:
                startCategoryGame();
                break;
            case 2:
                startGameFlow(null);
                break;
            case 3:
                ui.displayHowToPlay();
                break;
            case 4:
                showStatistics();
                break;
            case 5:
                break;
            default:
                break;
        }
    }

    private static void startCategoryGame() {
        ui.displayCategoryMenu();
        int choice = getChoice(0, 6);
        if (choice == 0) {
            return;
        }

        String category = null;
        switch (choice) {
            case 1:
                category = "Science";
                break;
            case 2:
                category = "History";
                break;
            case 3:
                category = "Math";
                break;
            case 4:
                category = "Geography";
                break;
            case 5:
                category = "Entertainment";
                break;
            case 6:
                category = null;
                break;
            default:
                return;
        }

        startGameFlow(category);
    }

    private static void startGameFlow(String category) {
        GameEngine engine = new GameEngine(questionBank, ui, category);
        engine.startGame();
    }

    private static void showStatistics() {
        String[] categories = {"Science", "History", "Math", "Geography", "Entertainment"};
        int[] counts = new int[categories.length];
        for (int i = 0; i < categories.length; i++) {
            counts[i] = questionBank.getQuestionsByCategory(categories[i]).size();
        }
        ui.displayStatisticsTable(categories, counts);
    }

    private static void exit() {
        System.out.print("Are you sure you want to leave? (Y/N): ");
        if (!input.hasNextLine()) {
            isRunning = false;
            System.out.println("No further input. Exiting.");
            return;
        }

        String answer = input.nextLine().trim();
        if (answer.equalsIgnoreCase("Y")) {
            isRunning = false;
        } else {
            System.out.println("Return to the main menu.");
        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.flush();
    }

    private static void showLoading(String message) {
        try {
            System.out.println("Initializing");
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted");
        }
    }

    private static void showWelcomeBanner() {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃               ★ QUIZ GAUNTLET ★                  ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃        American University of Phnom Penh         ┃");
        System.out.println("┃               Java Final Project                 ┃");
        System.out.println("┃                  Group 67 Gang                   ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃        >>> WELCOME TO QUIZ GAUNTLET <<<          ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃                 HOW TO PLAY                      ┃");
        System.out.println("┃  • User can play by Category or Random mode.     ┃");
        System.out.println("┃  • Answer questions and earn your score.         ┃");
        System.out.println("┃  • Can you conquer the Quiz Gauntlet?            ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}

