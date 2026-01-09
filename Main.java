import java.util.*;

public class Main {
    // KODE WARNA UNTUK CONSOLE
    public static final String RESET = "\u001B[0m";
    public static final String PINK = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";

    static Map<Integer, List<String>> rankingsByLevel = new HashMap<>();
    static String selectedAvatar = "🐱 Cat";

    // Update Judul Buku
    static String[] bookTitles = {"Cinderella 👸", "The Little Mermaid 🧜‍♀️"};

    // Update Isi Halaman Buku sesuai permintaan
    static String[][] bookPages = {
            {
                    "The story of Cinderella tells of a kindhearted young woman who was treated cruelly by her stepmother and sisters, but, never the less, kept a humble attitude.",
                    "One day, the king decided to throw a ball and invited all the young maidens in the kingdom. While Cinderella's sisters made her help them get ready, not once did they ask her to go.",
                    "Her Fairy Godmother appeared and helped Cinderella go to the ball with magic that would only last until midnight. At the ball, the prince and Cinderella danced all night.",
                    "When midnight came, Cinderella hurried away and one of her glass slippers fell off. The prince found this slipper and vowed to marry the girl who the slipper belonged to.",
                    "The prince reached Cinderella's house. Despite her family's efforts, the slipper was a perfect fit! She married the prince and lived happily ever after. Humility reaps rewards."
            },
            {
                    "In the underwater kingdom of Atlantica lived a little mermaid who loved nothing more than to look at the surface and observe how the humans lived.",
                    "She longed to be human, and after rescuing a handsome prince from drowning, she decided that she must become human at any cost to be with him.",
                    "She visited a sea witch who gave her human legs in exchange for her voice, on the condition she would become a slave if the prince did not marry her.",
                    "The mermaid went to her prince, but faced many challenges, ranging from the prince not recognising her to other suitors wanting to marry him.",
                    "At the end, the mermaid and prince reunite, defeating the witch and living happily ever after. Be brave enough to step into a new life for what your heart longs for."
            }
    };

    static class Question {
        String q, correct, emoji;
        List<String> options;
        Question(String q, String correct, String emoji, String... others) {
            this.q = q; this.correct = correct; this.emoji = emoji;
            this.options = new ArrayList<>(Arrays.asList(others));
            this.options.add(correct);
        }
    }

    public static void main(String[] args) {
        for(int i=1; i<=4; i++) rankingsByLevel.put(i, new ArrayList<>());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n" + CYAN + "========================================" + RESET);
            System.out.println("   🌈 ENGLISH QUEST CONSOLE v3.0 🌈   ");
            System.out.println(CYAN + "========================================" + RESET);
            System.out.println("1. Play Quiz 🎮");
            System.out.println("2. Read Books (Slides) 📖");
            System.out.println(GREEN + "3. View Ranking 🏆" + RESET); // Menu Ranking Hijau
            System.out.println("4. Credits ✨");
            System.out.println("5. Exit 🚪");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) playQuiz(sc);
            else if (choice.equals("2")) readBookSlides(sc);
            else if (choice.equals("3")) showRanking();
            else if (choice.equals("4")) showCredits();
            else if (choice.equals("5")) break;
        }
    }

    public static void playQuiz(Scanner sc) {
        System.out.println("\nSelect Level:");
        System.out.println(PINK + "1. Level 1: Colors 🎨" + RESET);
        System.out.println(BLUE + "2. Level 2: Animals 🐶" + RESET);
        System.out.println(YELLOW + "3. Level 3: Actions 🏃" + RESET);
        System.out.println(PURPLE + "4. Level 4: Master 👑" + RESET);
        System.out.print("Choice: ");
        int lvl = 1;
        try {
            lvl = Integer.parseInt(sc.nextLine());
        } catch (Exception e) { lvl = 1; }

        String lvlColor = switch(lvl) {
            case 1 -> PINK;
            case 2 -> BLUE;
            case 3 -> YELLOW;
            case 4 -> PURPLE;
            default -> RESET;
        };

        List<Question> qList = new ArrayList<>();
        // Contoh pertanyaan singkat untuk demo console
        if(lvl == 1) {
            qList.add(new Question("Banana?", "Yellow", "🍌", "Red", "Blue"));
            qList.add(new Question("Sky?", "Blue", "☁️", "Green", "Red"));
        } else if(lvl == 2) {
            qList.add(new Question("Long neck?", "Giraffe", "🦒", "Lion", "Cat"));
            qList.add(new Question("King?", "Lion", "🦁", "Ant", "Dog"));
        } else {
            qList.add(new Question("Opposite of Small?", "Big", "🐘", "Tiny", "Short"));
        }

        int score = 0;
        for(Question q : qList) {
            Collections.shuffle(q.options);
            System.out.println("\n" + q.emoji + " " + q.q);
            for(int i=0; i<q.options.size(); i++) {
                System.out.println(lvlColor + (char)('A'+i) + ". " + q.options.get(i) + RESET);
            }
            System.out.print("Answer (Type the word): ");
            if(sc.nextLine().equalsIgnoreCase(q.correct)) {
                score += 10;
                System.out.println("✅ Correct!");
            } else {
                System.out.println("❌ Wrong! The answer is: " + q.correct);
            }
        }

        System.out.println("\nFinal Score: " + score);
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();
        rankingsByLevel.get(lvl).add(name + " - " + score + " pts");
    }

    public static void showRanking() {
        System.out.println("\n" + GREEN + "🏆 LEADERBOARD PER LEVEL:" + RESET);
        String[] colors = {PINK, BLUE, YELLOW, PURPLE};
        for (int i = 1; i <= 4; i++) {
            System.out.println("\n" + colors[i-1] + "--- Level " + i + " ---" + RESET);
            List<String> r = rankingsByLevel.get(i);
            if (r.isEmpty()) System.out.println("No scores yet.");
            else r.forEach(s -> System.out.println("- " + s));
        }
    }

    public static void showCredits() {
        System.out.println("\n✨ DEVELOPERS ✨");
        System.out.println("Azra Auliya Putri, Azni Kamila, Nia Ramadhnai, M.Irzan Rahmadhani & Iqbal Syafra Dilla.");
    }

    public static void readBookSlides(Scanner sc) {
        System.out.println("\n" + YELLOW + "--- SELECT A BOOK ---" + RESET);
        for(int i=0; i<bookTitles.length; i++) System.out.println((i+1) + ". " + bookTitles[i]);
        System.out.print("Book Number: ");
        int bIdx;
        try {
            bIdx = Integer.parseInt(sc.nextLine()) - 1;
        } catch (Exception e) { bIdx = -1; }

        if(bIdx < 0 || bIdx >= bookTitles.length) return;

        String[] pages = bookPages[bIdx];
        for(int p=0; p<pages.length; p++) {
            System.out.println("\n" + CYAN + "------------------------------" + RESET);
            System.out.println(" 📖 " + bookTitles[bIdx] + " - Page " + (p+1) + " / " + pages.length);
            System.out.println("\n" + pages[p]);
            System.out.println("\n" + CYAN + "[Press Enter for Next Page]" + RESET);
            sc.nextLine();
        }
        System.out.println("✨ You finished the book! ✨");
    }
}