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
        FileExplorer fileExplorer = new FileExplorer(rootPath);
        label:
        while (true) {
            System.out.println();
            fileExplorer.printCurrentFile();
            String input = scanner.nextLine();
            switch (input) {
                case "q":
                    break label;
                case "r":
                    fileExplorer.reload();
                    break;
                case "..":
                    fileExplorer.goUp();
                    break;
                default:
                    fileExplorer.goTo(input);
                    break;
            }
        }
        System.out.println("Program terminated.");
    }
}
