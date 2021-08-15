import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileExplorer {
    private final static boolean DEBUGGING = true;

    private Node currentFile;
    private final String rootPath;
    private final Set<String> excludedPaths;

    public FileExplorer(String rootPath, Set<String> excludedPaths) throws FileNotFoundException {
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            throw new FileNotFoundException();
        }
        this.rootPath = rootPath;
        this.excludedPaths = excludedPaths;
        reload();
    }

    public void reload() {
        currentFile = initializeNode(new File(rootPath), null);
    }

    private Node initializeNode(File file, Node parent) {
        if (DEBUGGING) {
            System.out.println(file.getAbsolutePath());
        }
        if (excludedPaths.contains(file.getAbsolutePath())) {
            return new EFile("Excluded", parent, 0);
        }
        if (file.isFile()) {
            return new EFile(file.getName(), parent, file.length());
        }
        //File is directory
        if (file.isDirectory()) {
            Queue<Node> pq = new PriorityQueue<>();
            List<Node> contents = new ArrayList<>();
            File[] children = file.listFiles();
            EDirectory directoryNode = new EDirectory(file.getName(), parent, contents);
            if (children != null) {
                for (File child : children) {
                    pq.add(initializeNode(child, directoryNode));
                }
                while (!pq.isEmpty()) {
                    contents.add(pq.poll());
                }
            } else if (DEBUGGING) {
                System.out.println("Directory " + file.getAbsolutePath() + " has null children.");
            }
            directoryNode.calculateSize();
            return directoryNode;
        }
        if (DEBUGGING) {
            System.out.println("File" + file.getAbsolutePath() + "is neither file nor directory.");
        }
        return new EFile(file.getName(), parent, 0);
    }

    public void printCurrentFile() {
        currentFile.printSizeContents();
    }

    public void goUp() {
        if (currentFile.getParent() == null) {
            System.out.println("Can't go higher than root node.");
        } else {
            currentFile = currentFile.getParent();
        }
    }

    public void goTo(String child) {
        Node childNode = currentFile.getChild(child);
        if (childNode == null) {
            System.out.println("No such file.");
        } else {
            currentFile = childNode;
        }
    }
}
