import knightMovesChessBoard.model.Location;
import knightMovesChessBoard.service.BoardService;
import knightMovesChessBoard.service.impl.BoardServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardServiceTest {

    @Test
    public void testBoardForShortestPath(){
        BoardService boardService = new BoardServiceImpl();
        Location src = new Location(0,7,0);
        Location dest = new Location(7,0,Integer.MAX_VALUE);
        List<Location> moves = boardService.calculateKnightMoves(src,dest);
        assertEquals(6, moves.size());
        moves.forEach(move -> System.out.println(move.getX()+","+move.getY()));
    }

    @Test
    public void invalidCoordinates(){
        BoardService boardService = new BoardServiceImpl();
        Location src = new Location(0,10,0);
        Location dest = new Location(5,0,Integer.MAX_VALUE);
        List<Location> moves = boardService.calculateKnightMoves(src,dest);
        assertEquals(0, moves.size());
        moves.forEach(move -> System.out.println(move.getX()+","+move.getY()));
    }
}
