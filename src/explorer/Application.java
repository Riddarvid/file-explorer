package explorer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Application {
    public final Set<String> excludedPaths = new HashSet<>();
    private final Scanner scanner = new Scanner(System.in);

    public Application() {
        try {
            excludedPaths.addAll(Files.readAllLines(Path.of(this.getClass().getResource("Exclude.txt").toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
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
                    fileExplorer = new FileExplorer(input, excludedPaths);
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
