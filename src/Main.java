import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    private static final String PASSWORD = "admin1213";

    private static final int MIN_QUESTION = 20, MAX_QUESTION = 200;

    private static boolean isRunning = true;

    private static Object questionBank;

    private static Object ui;

    public static void main(String[] args){
        
        clearScreen();
        initializeApp();
        runningApp();
        System.out.println("Good Bye My Love..... Muah Muah kiss kiss");
    }

    private static void initializeApp(){
        showLoading("Initializing Quiz Gauntlet" );
        System.out.println("Ready!!");
    }

    private static void runningApp(){
        System.out.println("Running application..");
        while (isRunning){
            displayMainMenu();
            switch(getChoice(1, 3)) {
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
    
    private static void displayMainMenu(){
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

    private static int getChoice(int min, int max){
        int choice = 0;
        boolean valid = false;

        while (!valid){
            try {
                System.out.print("Choice: ");
                choice = input.nextInt();
                input.nextLine();

                if ((choice >= min) && (choice <= max)){
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

    private static void adminLogin(){
        System.out.println("Log in as Admin");
        System.out.print("Please enter the password: ");
        String enteredPassword = input.nextLine(); 

        if (enteredPassword.equals(PASSWORD)) {
            System.out.println("Login Successful!");
            adminMenu();
        } else {
            System.out.println("Incorrect Password!");
        }
    }

    private static void adminMenu(){
    System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
    System.out.println("┃                ⚙ ADMIN MENU ⚙                    ┃");
    System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
    System.out.println("┃  ➤ [1] Create Question                           ┃");
    System.out.println("┃  ➤ [2] Review Question                           ┃");
    System.out.println("┃  ➤ [3] Update Question                           ┃");
    System.out.println("┃  ➤ [4] Delete Question                           ┃");
    System.out.println("┃  ➤ [5] Back                                      ┃");
    System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    getChoice(1,5);
    System.out.println();
        
    }

    private static void userMenu(){
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                 🎮 USER MENU 🎮                  ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃  ➤ [1] Play by Categories                        ┃");
        System.out.println("┃  ➤ [2] Play by Random                            ┃");
        System.out.println("┃  ➤ [3] View Statistics                           ┃");
        System.out.println("┃  ➤ [4] Back                                      ┃");    
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        getChoice(1,5);
    }

    private static void exit(){
        System.out.println("Are you sure you want to leave? (Y/N): ");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Y")){
            isRunning = false;
            input.close(); 
        }
        
    }

    private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }
    private static void showLoading(String message){
        try{
            System.out.println("Initializing");
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            System.out.println();
        }
        catch (InterruptedException e){
            System.out.println("Loading interrupted");
        }
    }
    
    private static void showWelcomeBanner(){
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

