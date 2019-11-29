package com.snake;

import com.snake.model.Board;
import com.snake.model.Player;

public class Application {
    public static void main(String[] args) {
            Board board  = new Board(4, 4, new Player());
            board.playGame();
    }
}
