package knightMovesChessBoard.controller;

import knightMovesChessBoard.model.Location;
import knightMovesChessBoard.service.BoardService;
import knightMovesChessBoard.service.impl.BoardServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @CrossOrigin
    @RequestMapping("/calculateMoves/x1={x1}&y1={y1}&x2={x2}&y2={y2}")
    public List<Location> calculateMoves(@PathVariable int x1, @PathVariable int y1, @PathVariable int x2, @PathVariable int y2){
        BoardService boardService = new BoardServiceImpl();
        Location start = new Location(x1, y1, 0);
        Location destination = new Location(x2, y2, Integer.MAX_VALUE);

        return boardService.calculateKnightMoves(start, destination);
    }
}
