package knightMovesChessBoard.service.impl;

import knightMovesChessBoard.model.Location;
import knightMovesChessBoard.service.BoardService;

import java.util.*;

public class BoardServiceImpl implements BoardService{

    private static Location[][] chessboard = new Location[8][8];

    @Override
    public List<Location> calculateKnightMoves(Location start, Location finish){
        List<Location> locationsVisited = new ArrayList<>();
        Iterable<Location> steps = calculateNumOfSteps(start, finish);
        if (steps != null) {
            steps.forEach(locationsVisited::add);
        }
        return locationsVisited;
    }

    private Iterable<Location> calculateNumOfSteps(Location start, Location finish){
        Queue<Location> queue = new LinkedList<>();
        populateChessBoard();
        //Assign starting depth for the source as 0
        chessboard[0][1] = new Location(start.getX(), start.getY(), 0);
        queue.add(start);
        while (queue.size() != 0){
            Location currentLocation = queue.poll();
            int distance = currentLocation.getDepth();
            if(finish.equals(currentLocation)) {
                return getShortestPath(start, finish);
            } else {
                breadthFirstSearch(currentLocation, ++distance, queue);
            }
        }
        return null;
    }

    private Iterable<Location> getShortestPath(Location start, Location finish) {
        Stack<Location> path = new Stack<>();
        Location current = chessboard[finish.getX()][finish.getY()];
        while(! current.equals(start)) {
            path.add(current);
            current = chessboard[current.getX()][current.getY()];
        }
        path.add(new Location(start.getX(), start.getY(), 0));
        return path;
    }

    private static boolean isLocationValid(Location current, Location next) {
        // Use Pythagoras theorem to ensure that a move makes a right-angled triangle with sides of 1 and 2. 1-squared + 2-squared is 5.
        int deltaR = next.getX() - current.getX();
        int deltaC = next.getY() - current.getY();
        return 5 == deltaR * deltaR + deltaC * deltaC;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    /*Populate initial chessboard values*/
    private static void populateChessBoard() {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length; j++) {
                chessboard[i][j] = new Location(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        }
    }

    private static void breadthFirstSearch(Location current, int depth, Queue queue) {
        // Start from -2 to +2 range and start marking each location on the board
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                Location nextLocation = new Location(current.getX() + i, current.getY() + j, depth);
                if(inRange(nextLocation.getX(), nextLocation.getY())) {
                    //Skip if next location is same as the location you came from in previous run
                    if(current.equals(nextLocation)){
                        continue;
                    }
                    if (isLocationValid(current, nextLocation)) {
                        Location position = chessboard[nextLocation.getX()][nextLocation.getY()] ;
                        if (position.getDepth() > depth) {
                            chessboard[current.getX() + i][current.getY() + j] = new Location(current.getX(), current.getY(), depth);
                            queue.add(nextLocation);
                        }
                    }
                }
            }
        }
    }
}
