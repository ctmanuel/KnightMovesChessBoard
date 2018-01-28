import knightMovesChessBoard.model.Location;
import knightMovesChessBoard.service.BoardService;
import knightMovesChessBoard.service.impl.BoardServiceImpl;
import org.junit.Test;

import java.util.List;

public class BoardServiceTest {

    @Test
    public void testBoardForShortestPath(){
        BoardService boardService = new BoardServiceImpl();
        Location src = new Location(0,7,0);
        Location dest = new Location(7,0,Integer.MAX_VALUE);
        List<Location> moves = boardService.calculateKnightMoves(src,dest);
        moves.forEach(move -> System.out.println(move.getX()+","+move.getY()));
    }
}
