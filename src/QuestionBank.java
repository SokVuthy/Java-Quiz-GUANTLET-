import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestionBank {

    private final ArrayList<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        loadSampleQuestions();
    }

    // =========================================================================
    // CREATE
    // =========================================================================

    public void addQuestion(Question q) {
        questions.add(q);
        System.out.println("[ADDED] " + q);
    }

    public void addQuestionFromInput(Scanner input) {
        System.out.println("Enter the question text:");
        String questionText = input.nextLine();

        System.out.println("Enter option A:");
        String optionA = input.nextLine();

        System.out.println("Enter option B:");
        String optionB = input.nextLine();

        System.out.println("Enter option C:");
        String optionC = input.nextLine();

        System.out.println("Enter option D:");
        String optionD = input.nextLine();

        char correctAnswer = getValidatedAnswer(input);

        System.out.println("Enter category (Science/History/Math/Geography/Entertainment):");
        String category = input.nextLine();

        Question newQuestion = new Question(questionText, optionA, optionB, optionC, optionD, correctAnswer, category);
        addQuestion(newQuestion);
    }

    private char getValidatedAnswer(Scanner input) {
        while (true) {
            System.out.println("Enter the correct answer (A/B/C/D):");
            String answerInput = input.nextLine().trim().toUpperCase();
            if (answerInput.length() == 1 && "ABCD".contains(answerInput)) {
                return answerInput.charAt(0);
            }
            System.out.println("Invalid input. Please enter A, B, C, or D.");
        }
    }

    // =========================================================================
    // READ
    // =========================================================================

    public ArrayList<Question> getAllQuestions() {
        return questions;
    }

    public Question getQuestionById(int id) {
        for (Question q : questions) {
            if (q.getID() == id) return q;
        }
        return null;
    }

    public ArrayList<Question> getQuestionsByCategory(String category) {
        ArrayList<Question> result = new ArrayList<>();
        for (Question q : questions) {
            if (q.getCategories().equalsIgnoreCase(category)) result.add(q);
        }
        return result;
    }

    public ArrayList<Question> getRandomQuestions(int count) {
        ArrayList<Question> copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        if (count > copy.size()) count = copy.size();
        return new ArrayList<>(copy.subList(0, count));
    }

    public ArrayList<Question> getRandomQuestionsByCategory(String category, int count) {
        ArrayList<Question> categoryQuestions = getQuestionsByCategory(category);
        Collections.shuffle(categoryQuestions);
        if (count > categoryQuestions.size()) count = categoryQuestions.size();
        return new ArrayList<>(categoryQuestions.subList(0, count));
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    // =========================================================================
    // UPDATE
    // =========================================================================

    public boolean updateQuestion(int id, Question updatedQuestion) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getID() == id) {
                questions.set(i, updatedQuestion);
                return true;
            }
        }
        System.out.println("[ERROR] Question #" + id + " not found.");
        return false;
    }

    // =========================================================================
    // DELETE
    // =========================================================================

    public void deleteQuestion(int id) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getID() == id) {
                Question removed = questions.remove(i);
                System.out.println("[DELETED] " + removed);
                return;
            }
        }
        System.out.println("[ERROR] Question #" + id + " not found.");
    }

    // =========================================================================
    // STATISTICS
    // =========================================================================

    public void printCategoryStats() {
        String[] categories = {"Science", "History", "Math", "Geography", "Entertainment"};
        System.out.println("\n===== CATEGORY STATISTICS =====");
        System.out.printf("%-20s %s%n", "Category", "# of Questions");
        System.out.println("----------------------------------");
        for (String cat : categories) {
            System.out.printf("%-20s %d%n", cat, getQuestionsByCategory(cat).size());
        }
        System.out.println("----------------------------------");
        System.out.printf("%-20s %d%n", "TOTAL", questions.size());
        System.out.println("==================================\n");
    }

    // =========================================================================
    // DISPLAY
    // =========================================================================

    public void displayQuestionsByCategory(String category) {
        ArrayList<Question> filtered = getQuestionsByCategory(category);
        if (filtered.isEmpty()) {
            System.out.println("[EMPTY] No questions found for category: " + category);
            return;
        }
        System.out.println("\n===== " + category.toUpperCase() + " (" + filtered.size() + " questions) =====\n");
        for (Question q : filtered) {
            q.display();
            System.out.println("-----------------------------");
        }
    }

    // =========================================================================
    // SAMPLE DATA
    // =========================================================================

    private void loadSampleQuestions() {

        // ----- SCIENCE (20) -----
        questions.add(new Question("What is the chemical symbol for water?", "HO", "H2O", "WA", "OX2", 'B', "Science"));
        questions.add(new Question("How many bones are in the adult human body?", "206", "186", "226", "196", 'A', "Science"));
        questions.add(new Question("What planet is known as the Red Planet?", "Venus", "Jupiter", "Mars", "Saturn", 'C', "Science"));
        questions.add(new Question("What is the powerhouse of the cell?", "Nucleus", "Ribosome", "Mitochondria", "Vacuole", 'C', "Science"));
        questions.add(new Question("What gas do plants absorb from the atmosphere?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", 'B', "Science"));
        questions.add(new Question("What is the speed of light (approx.) in km/s?", "150,000", "200,000", "300,000", "400,000", 'C', "Science"));
        questions.add(new Question("Which planet has the most moons?", "Jupiter", "Mars", "Neptune", "Saturn", 'D', "Science"));
        questions.add(new Question("What is the atomic number of Carbon?", "4", "6", "8", "12", 'B', "Science"));
        questions.add(new Question("What force keeps planets in orbit around the sun?", "Magnetism", "Friction", "Gravity", "Nuclear force", 'C', "Science"));
        questions.add(new Question("What is the most abundant gas in Earth's atmosphere?", "Oxygen", "Carbon Dioxide", "Hydrogen", "Nitrogen", 'D', "Science"));
        questions.add(new Question("What organ pumps blood through the human body?", "Liver", "Brain", "Heart", "Lungs", 'C', "Science"));
        questions.add(new Question("What type of rock is formed from cooled lava?", "Sedimentary", "Metamorphic", "Igneous", "Limestone", 'C', "Science"));
        questions.add(new Question("What is the chemical symbol for Gold?", "Go", "Gd", "Ag", "Au", 'D', "Science"));
        questions.add(new Question("How many chromosomes do humans normally have?", "23", "44", "46", "48", 'C', "Science"));
        questions.add(new Question("What is the unit of electrical resistance?", "Volt", "Watt", "Ohm", "Ampere", 'C', "Science"));
        questions.add(new Question("Which vitamin is produced when skin is exposed to sunlight?", "Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D", 'D', "Science"));
        questions.add(new Question("What is the boiling point of water in Celsius?", "90C", "95C", "100C", "110C", 'C', "Science"));
        questions.add(new Question("What part of the plant performs photosynthesis?", "Root", "Stem", "Leaf", "Flower", 'C', "Science"));
        questions.add(new Question("What is the hardest natural substance on Earth?", "Gold", "Iron", "Diamond", "Quartz", 'C', "Science"));
        questions.add(new Question("Which blood type is known as the universal donor?", "A+", "B-", "AB+", "O-", 'D', "Science"));

        // ----- HISTORY (20) -----
        questions.add(new Question("In what year did World War II end?", "1943", "1944", "1945", "1946", 'C', "History"));
        questions.add(new Question("Who was the first President of the United States?", "John Adams", "Thomas Jefferson", "George Washington", "Benjamin Franklin", 'C', "History"));
        questions.add(new Question("Which ancient wonder was located in Alexandria?", "The Colosseum", "The Great Library", "The Lighthouse", "The Hanging Gardens", 'C', "History"));
        questions.add(new Question("What year did the Berlin Wall fall?", "1987", "1988", "1989", "1990", 'C', "History"));
        questions.add(new Question("Who painted the Mona Lisa?", "Michelangelo", "Raphael", "Leonardo da Vinci", "Donatello", 'C', "History"));
        questions.add(new Question("Which empire was ruled by Julius Caesar?", "Greek", "Ottoman", "Roman", "Byzantine", 'C', "History"));
        questions.add(new Question("In what year did the Titanic sink?", "1910", "1911", "1912", "1913", 'C', "History"));
        questions.add(new Question("Who was the first human to walk on the Moon?", "Buzz Aldrin", "Yuri Gagarin", "Neil Armstrong", "John Glenn", 'C', "History"));
        questions.add(new Question("What ancient civilization built Machu Picchu?", "Aztec", "Maya", "Inca", "Olmec", 'C', "History"));
        questions.add(new Question("Which country was the first to grant women the right to vote?", "USA", "UK", "Australia", "New Zealand", 'D', "History"));
        questions.add(new Question("Who wrote the Declaration of Independence?", "George Washington", "Benjamin Franklin", "Thomas Jefferson", "John Adams", 'C', "History"));
        questions.add(new Question("What year did World War I begin?", "1912", "1913", "1914", "1915", 'C', "History"));
        questions.add(new Question("Which city was the capital of ancient Egypt?", "Cairo", "Thebes", "Memphis", "Alexandria", 'C', "History"));
        questions.add(new Question("Who was the first female Prime Minister of the UK?", "Queen Victoria", "Margaret Thatcher", "Theresa May", "Angela Merkel", 'B', "History"));
        questions.add(new Question("What was the first artificial satellite in space?", "Apollo 1", "Voyager", "Sputnik", "Explorer", 'C', "History"));
        questions.add(new Question("In what year did the French Revolution begin?", "1776", "1783", "1789", "1799", 'C', "History"));
        questions.add(new Question("Who was known as the Maid of Orleans?", "Catherine the Great", "Marie Curie", "Joan of Arc", "Eleanor of Aquitaine", 'C', "History"));
        questions.add(new Question("Which war was fought between the North and South of America?", "The Revolutionary War", "The Civil War", "The War of 1812", "The Mexican War", 'B', "History"));
        questions.add(new Question("What country did Adolf Hitler originally come from?", "Germany", "Poland", "Austria", "Switzerland", 'C', "History"));
        questions.add(new Question("Who discovered America in 1492?", "Amerigo Vespucci", "Vasco da Gama", "Ferdinand Magellan", "Christopher Columbus", 'D', "History"));

        // ----- MATH (20) -----
        questions.add(new Question("What is the value of Pi (to 2 decimal places)?", "3.12", "3.14", "3.16", "3.18", 'B', "Math"));
        questions.add(new Question("What is the square root of 144?", "11", "12", "13", "14", 'B', "Math"));
        questions.add(new Question("What is 15% of 200?", "20", "25", "30", "35", 'C', "Math"));
        questions.add(new Question("What is 7 factorial (7!)?", "2520", "5040", "720", "40320", 'B', "Math"));
        questions.add(new Question("How many sides does a hexagon have?", "5", "6", "7", "8", 'B', "Math"));
        questions.add(new Question("What is 2 to the power of 10?", "512", "1024", "2048", "256", 'B', "Math"));
        questions.add(new Question("What is the sum of angles in a triangle?", "90", "180", "270", "360", 'B', "Math"));
        questions.add(new Question("What is the area of a circle with radius 5? (pi=3.14)", "31.4", "62.8", "78.5", "15.7", 'C', "Math"));
        questions.add(new Question("What is the prime factorization base of 36?", "2 and 5", "2 and 3", "3 and 5", "2 and 7", 'B', "Math"));
        questions.add(new Question("What is the least common multiple of 4 and 6?", "8", "10", "12", "24", 'C', "Math"));
        questions.add(new Question("Solve: 3x + 9 = 24. What is x?", "3", "4", "5", "6", 'C', "Math"));
        questions.add(new Question("What is the median of: 3, 7, 9, 2, 5?", "5", "7", "9", "3", 'A', "Math"));
        questions.add(new Question("How many degrees in a right angle?", "45", "60", "90", "120", 'C', "Math"));
        questions.add(new Question("What is 250 divided by 0.5?", "125", "500", "250", "50", 'B', "Math"));
        questions.add(new Question("What is the hypotenuse of a right triangle with legs 3 and 4?", "6", "7", "5", "8", 'C', "Math"));
        questions.add(new Question("What does the Roman numeral C represent?", "50", "500", "100", "1000", 'C', "Math"));
        questions.add(new Question("What is 12 squared?", "124", "132", "144", "152", 'C', "Math"));
        questions.add(new Question("What is the next prime number after 11?", "12", "13", "14", "15", 'B', "Math"));
        questions.add(new Question("What is 1000 - 437?", "563", "553", "573", "543", 'A', "Math"));
        questions.add(new Question("What is the perimeter of a square with side length 7?", "14", "21", "28", "49", 'C', "Math"));

        // ----- GEOGRAPHY (20) -----
        questions.add(new Question("What is the capital of Japan?", "Seoul", "Beijing", "Tokyo", "Bangkok", 'C', "Geography"));
        questions.add(new Question("Which is the longest river in the world?", "Amazon", "Yangtze", "Mississippi", "Nile", 'D', "Geography"));
        questions.add(new Question("On which continent is the Sahara Desert?", "Asia", "South America", "Australia", "Africa", 'D', "Geography"));
        questions.add(new Question("What is the smallest country in the world?", "Monaco", "San Marino", "Vatican City", "Liechtenstein", 'C', "Geography"));
        questions.add(new Question("Which country has the largest population?", "USA", "India", "China", "Indonesia", 'C', "Geography"));
        questions.add(new Question("What is the largest ocean in the world?", "Atlantic", "Indian", "Arctic", "Pacific", 'D', "Geography"));
        questions.add(new Question("What is the capital of Australia?", "Sydney", "Melbourne", "Canberra", "Brisbane", 'C', "Geography"));
        questions.add(new Question("Which mountain is the tallest in the world?", "K2", "Kangchenjunga", "Mount Everest", "Lhotse", 'C', "Geography"));
        questions.add(new Question("In which country is the Amazon River primarily located?", "Colombia", "Peru", "Venezuela", "Brazil", 'D', "Geography"));
        questions.add(new Question("What is the capital of Canada?", "Toronto", "Vancouver", "Ottawa", "Montreal", 'C', "Geography"));
        questions.add(new Question("Which country owns the Galapagos Islands?", "Peru", "Chile", "Ecuador", "Colombia", 'C', "Geography"));
        questions.add(new Question("What is the largest country by land area?", "Canada", "China", "USA", "Russia", 'D', "Geography"));
        questions.add(new Question("Which sea is the saltiest in the world?", "Red Sea", "Dead Sea", "Caspian Sea", "Mediterranean Sea", 'B', "Geography"));
        questions.add(new Question("What is the capital of Brazil?", "Rio de Janeiro", "Sao Paulo", "Brasilia", "Salvador", 'C', "Geography"));
        questions.add(new Question("Which country is both a continent and a country?", "Greenland", "Iceland", "New Zealand", "Australia", 'D', "Geography"));
        questions.add(new Question("How many countries are in Africa?", "44", "48", "54", "60", 'C', "Geography"));
        questions.add(new Question("Which river flows through Egypt?", "Niger", "Congo", "Nile", "Zambezi", 'C', "Geography"));
        questions.add(new Question("What is the capital of South Korea?", "Busan", "Incheon", "Pyongyang", "Seoul", 'D', "Geography"));
        questions.add(new Question("On which continent is Argentina located?", "North America", "Central America", "Europe", "South America", 'D', "Geography"));
        questions.add(new Question("What is the capital of Kenya?", "Lagos", "Nairobi", "Accra", "Dakar", 'B', "Geography"));

        // ----- ENTERTAINMENT (20) -----
        questions.add(new Question("Which movie features the quote I'll be back?", "RoboCop", "Die Hard", "The Terminator", "Predator", 'C', "Entertainment"));
        questions.add(new Question("What animated film features a character named Simba?", "Bambi", "Jungle Book", "The Lion King", "Tarzan", 'C', "Entertainment"));
        questions.add(new Question("Which TV show features dragons and the Iron Throne?", "Vikings", "The Witcher", "Merlin", "Game of Thrones", 'D', "Entertainment"));
        questions.add(new Question("Who played Iron Man in the Marvel movies?", "Chris Evans", "Chris Hemsworth", "Robert Downey Jr.", "Mark Ruffalo", 'C', "Entertainment"));
        questions.add(new Question("What is the highest-grossing movie of all time?", "Titanic", "Avengers Endgame", "Avatar", "The Lion King", 'C', "Entertainment"));
        questions.add(new Question("Which band sang Bohemian Rhapsody?", "The Beatles", "Led Zeppelin", "Queen", "The Rolling Stones", 'C', "Entertainment"));
        questions.add(new Question("What streaming platform is known for Stranger Things?", "HBO", "Hulu", "Netflix", "Disney+", 'C', "Entertainment"));
        questions.add(new Question("In which movie does a clownfish search for his son?", "Shark Tale", "Finding Dory", "Finding Nemo", "The Little Mermaid", 'C', "Entertainment"));
        questions.add(new Question("Who is the author of the Harry Potter series?", "Suzanne Collins", "Stephenie Meyer", "J.K. Rowling", "C.S. Lewis", 'C', "Entertainment"));
        questions.add(new Question("What video game franchise features a character named Link?", "Final Fantasy", "The Legend of Zelda", "Metroid", "Fire Emblem", 'B', "Entertainment"));
        questions.add(new Question("Which pop star is known as the Queen of Pop?", "Beyonce", "Rihanna", "Lady Gaga", "Madonna", 'D', "Entertainment"));
        questions.add(new Question("What is the name of Batman's butler?", "James", "Harold", "Alfred", "Edwin", 'C', "Entertainment"));
        questions.add(new Question("Which movie studio created the Toy Story franchise?", "DreamWorks", "Universal", "Pixar", "Sony", 'C', "Entertainment"));
        questions.add(new Question("Who sang Thriller in 1982?", "Prince", "Michael Jackson", "David Bowie", "Elton John", 'B', "Entertainment"));
        questions.add(new Question("What is the name of the fictional kingdom in Frozen?", "Narnia", "Genovia", "Arendelle", "Eldorado", 'C', "Entertainment"));
        questions.add(new Question("Which TV show is set in the fictional town of Hawkins Indiana?", "Riverdale", "The OC", "Stranger Things", "Twin Peaks", 'C', "Entertainment"));
        questions.add(new Question("Who played the Joker in The Dark Knight 2008?", "Jared Leto", "Joaquin Phoenix", "Jack Nicholson", "Heath Ledger", 'D', "Entertainment"));
        questions.add(new Question("What is the best-selling video game console of all time?", "Xbox 360", "Nintendo Wii", "PlayStation 2", "Game Boy", 'C', "Entertainment"));
        questions.add(new Question("Which movie won the first Academy Award for Best Picture?", "All Quiet on the Western Front", "Wings", "Sunrise", "The Broadway Melody", 'B', "Entertainment"));
        questions.add(new Question("What color is the pill Neo takes in The Matrix?", "Blue", "White", "Red", "Green", 'C', "Entertainment"));

        System.out.println("Question bank loaded with " + questions.size() + " questions across 5 categories.");
    }
}