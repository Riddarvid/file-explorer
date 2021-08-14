public class EFile extends Node {
    public EFile(String name, Node parent, long size) {
        super(name, parent);
        this.size = size;
    }

    @Override
    public void printSizeContents() {
        System.out.println("Size of current file " + getName() + ": " + getSizeReadable());
    }

    @Override
    public Node getChild(String child) {
        return null;
    }
}
