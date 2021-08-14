public abstract class Node implements Comparable<Node> {
    private final static long SIZE_OF_KB = 1024;
    private final static long SIZE_OF_MB = 1024 * SIZE_OF_KB;
    private final static long SIZE_OF_GB = 1024 * SIZE_OF_MB;

    public final static long UPPER_THRESHOLD = 10 * SIZE_OF_GB;
    public final static long LOWER_THRESHOLD = SIZE_OF_GB;
    private final static String RED = "\033[0;31m";
    private final static String RESET = "\033[0m";

    private final String name;
    private final Node parent;
    protected long size;

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public long getSize() {
        return size;
    }

    public abstract void printSizeContents();

    public String getName() {
        return name;
    }

    public String getSizeReadable() {
        StringBuilder sb = new StringBuilder();
        if (size >= UPPER_THRESHOLD) {
            sb.append(RED);
        }
        if (size > SIZE_OF_GB) {
            sb.append(size / SIZE_OF_GB).append(" GB");
        } else if (size > SIZE_OF_MB) {
            sb.append(size / SIZE_OF_MB).append(" MB");
        } else if (size > SIZE_OF_KB) {
            sb.append(size / SIZE_OF_KB).append(" KB");
        } else {
            sb.append(size).append(" bytes");
        }
        if (size > UPPER_THRESHOLD) {
            sb.append(RESET);
        }
        return sb.toString();
    }

    public Node getParent() {
        return parent;
    }

    public abstract Node getChild(String child);

    @Override
    public int compareTo(Node node) {
        return -Long.compare(this.size, node.size);
    }
}
