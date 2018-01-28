package knightMovesChessBoard.model;

public class Location {
    private int x;
    private int y;
    private int depth;


    public Location(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDepth() {
        return depth;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Location that) {
        return this.x == that.x && this.y == that.y;
    }
}
