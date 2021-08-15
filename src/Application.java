import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    private final static String rootPath = "/";
    public final static Set<String> excludedPaths = new HashSet<>();
    private final Scanner scanner = new Scanner(System.in);

    static {
        excludedPaths.add("/home/riddarvid/.steam");
        excludedPaths.add("/home/riddarvid/.local/share/Steam");
        excludedPaths.add("/proc");
        excludedPaths.add("/sys");
        excludedPaths.add("/home");
    }

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        FileExplorer fileExplorer = null;
        boolean running = true;
        while (running) {
            System.out.println("Please enter a root directory.");
            String input = scanner.nextLine();
            if (input.equals("q")) {
                running = false;
            } else {
                try {
                    fileExplorer = new FileExplorer(input);
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("That file does not exist.");
                }
            }
        }
        while (running) {
            System.out.println();
            fileExplorer.printCurrentFile();
            String input = scanner.nextLine();
            switch (input) {
                case "q" -> running = false;
                case "r" -> fileExplorer.reload();
                case ".." -> fileExplorer.goUp();
                default -> fileExplorer.goTo(input);
            }
        }
        System.out.println("Program terminated.");
    }
}
