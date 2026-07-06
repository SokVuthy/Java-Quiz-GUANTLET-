
import java.util.Scanner;
import java.util.InputMismatchException;
public class Main{
private static final Scanner input = new Scanner(System.in);
private static final String PASSWORD = "admin1213";
private static boolean isRunning = true;

private static QuestionBank questionBank;
private static UserInterface ui;

public static void main(String[] args) {
    clearScreen();
    initializeApp();
    while (isRunning) {
        runningApp();
    }
    ui.displayExitMessage();
}

private static void initializeApp() {
    showLoading();
    questionBank = new QuestionBank();
    ui = new UserInterface(input);
    ui.displayWelcomeBanner();
    IO.println("Ready!!");
}

private static void runningApp() {
    IO.println("Running application..");
    while (isRunning) {
        displayMainMenu();
        switch (getChoice(0, 3)) {
            case 1:
                userMenu();
                break;
            case 2:
                adminLogin();
                break;
            case 3:
                showStatistics();
                break;
            case 0:
                exit();
                break;
            default:
                IO.println("Choice must be 0-3");
                break;
        }
    }
}

private static int getChoice(int min, int max) {
    int choice = 0;
    boolean valid = false;

    while (!valid) {
        try {
            IO.print("Choice: ");
            if (!input.hasNextInt()) {
                if (!input.hasNextLine()) {
                    isRunning = false;
                    return min;
                }
                input.nextLine();
                IO.println("Error: Input must be a number!!!!");
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            if ((choice >= min) && (choice <= max)) {
                valid = true;
            } else {
                IO.println("Choice must be " + min + "-" + max);
            }
        } catch (InputMismatchException e) {
            IO.println("Error: Input must be a number!!!!");
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
    ui.displayWelcomeBanner();
    ui.displayMainMenu();
}

private static void adminMenu() {
    boolean inAdminMenu = true;
    while (inAdminMenu) {
        ui.displayAdminMenu();
        int choice = getChoice(0, 6);
        switch (choice) {
            case 1:
                ui.displayAllQuestions(questionBank.getAllQuestions());
                break;
            case 2:
                questionBank.addQuestionFromInput(input);
                break;
            case 3: {
                int id = getIntInput("Which question do you want to update? (ID): ");
                Question existQuestion = questionBank.getQuestionById(id);
                if (existQuestion == null) {
                    IO.println("Question Not Found!");
                    break;
                }
                IO.println("\nEnter new question details:");
                String text = ui.getStringInput("Question text: ");
                String optA = ui.getStringInput("Option A: ");
                String optB = ui.getStringInput("Option B: ");
                String optC = ui.getStringInput("Option C: ");
                String optD = ui.getStringInput("Option D: ");
                char correct = ui.getCharInput("Correct answer (A/B/C/D): ");
                String category = ui.getStringInput("Category: ");
                existQuestion.updateFields(text, optA, optB, optC, optD, correct, category);
                IO.println("[OK] Question #" + id + " updated successfully.");
                break;
            }
            case 4: {
                int id = getIntInput("Which question do you want to delete? (ID): ");
                Question existQuestion = questionBank.getQuestionById(id);
                if (existQuestion == null) {
                    IO.println("Question Not Found!");
                    break;
                }
                questionBank.deleteQuestion(id);
                break;
            }
            case 5:
                ui.displayDivider();
                questionBank.printCategoryStats();
                showStatistics();
                break;
            case 6: {
                String category = ui.getStringInput("Category name: ");
                questionBank.displayQuestionsByCategory(category);
                break;
            }
            default:
                inAdminMenu = false;
                break;
        }
    }
}

private static int getIntInput(String prompt) {
    while (true) {
        try {
            IO.print(prompt);
            String raw = input.nextLine().trim();
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            ui.displayError("Please enter a valid number.");
        }
    }
}

private static void userMenu() {
    boolean inUserMenu = true;

    while (inUserMenu) {
        IO.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        IO.println("┃                 🎮 USER MENU 🎮                  ┃");
        IO.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        IO.println("┃  ➤ [1] Play by Categories                        ┃");
        IO.println("┃  ➤ [2] Play by Random                            ┃");
        IO.println("┃  ➤ [3] How to Play                               ┃");
        IO.println("┃  ➤ [4] View Statistics                           ┃");
        IO.println("┃  ➤ [5] Back                                      ┃");
        IO.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
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
                inUserMenu = false;
                break;
            default:

                break;
        }
    }
}

private static void startCategoryGame() {
    ui.displayCategoryMenu();
    int choice = getChoice(0, 6);
    if (choice == 0) {
        return;
    }
    String category;
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
    ui.displayMessage("Total questions available: " + questionBank.getTotalQuestions());
    String[] categories = {"Science", "History", "Math", "Geography", "Entertainment"};
    int[] counts = new int[categories.length];
    for (int i = 0; i < categories.length; i++) {
        counts[i] = questionBank.getQuestionsByCategory(categories[i]).size();
    }
    ui.displayStatisticsTable(categories, counts);
}

private static void exit() {
    IO.print("Are you sure you want to leave? (Y/N): ");
    if (!input.hasNextLine()) {
        isRunning = false;
        IO.println("No further input. Exiting.");
        return;
    }

    String answer = input.nextLine().trim();
    if (answer.equalsIgnoreCase("Y")) {
        isRunning = false;
    } else {
        IO.println("Return to the main menu.");
    }
}

private static void clearScreen() {
    for (int i = 0; i < 50; i++) {
        IO.println();
    }
    System.out.flush();
}

private static void showLoading() {
    try {
        IO.println("Initializing");
        IO.println(".");
        Thread.sleep(500);
        IO.println(".");
        Thread.sleep(500);
        IO.println(".");
        Thread.sleep(500);
        IO.println(".");
        IO.println();
    } catch (InterruptedException e) {
        IO.println("Loading interrupted");
    }
}
}
