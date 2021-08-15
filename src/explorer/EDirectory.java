package explorer;

import java.util.List;

public class EDirectory extends Node {
    private final List<Node> contents;

    public EDirectory(String name, Node parent, List<Node> contents) {
        super(name, parent);
        this.contents = contents;
    }

    public void calculateSize() {
        size = 0;
        for (Node node : contents) {
            size += node.getSize();
        }
    }

    @Override
    public void printSizeContents() {
        System.out.println("Size of current directory " + getName() + ": " + getSizeReadable());
        System.out.println();
        System.out.println("Size of contents:");
        for (Node child : contents) {
            if (child.getSize() >= LOWER_THRESHOLD) {
                System.out.println(child.getName() + ": " + child.getSizeReadable());
            }
        }
    }

    @Override
    public Node getChild(String child) {
        for (Node childNode : contents) {
            if (childNode.getName().equals(child)) {
                return childNode;
            }
        }
        return null;
    }
}
