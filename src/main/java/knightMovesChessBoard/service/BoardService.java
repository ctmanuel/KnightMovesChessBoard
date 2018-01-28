package knightMovesChessBoard.service;

import knightMovesChessBoard.model.Location;

import java.util.*;

public interface BoardService {
    List<Location> calculateKnightMoves(Location start, Location finish);
}
