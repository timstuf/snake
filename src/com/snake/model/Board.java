package com.snake.model;

import java.util.*;

public class Board {
    private final int width;
    private final int height;
    private List<Point> foodBlocks;
    private Snake snake;
    private final Player player;

    public Board(int width, int height, Player player) {
        this.width = width;
        this.height = height;
        List<Point> points = new ArrayList<>();
        points.add(new Point(width / 2, height - 1));
        this.snake = new Snake(points, Snake.Direction.UP);
        this.player = player;
        this.foodBlocks = createTestFoodBlocks();
    }

    private List<Point> createTestFoodBlocks(){
        List<Point> blocks = new ArrayList<>();
        blocks.add(new Point(0, 1));
        blocks.add(new Point(3, 3));
        blocks.add(new Point(2, 1));
        return blocks;
    }

    public void playGame() {
        while (true) {
            System.out.println(getStringState());
            MoveEvent moveEvent = player.getNextMoveEvent();
            Snake newSnake = snake.move(moveEvent.getType());
            if (newSnake.crossedItself() || !allPointsOnBoard(newSnake.getPoints())) {
                System.out.println("GAME OVER");
                break;
            }
            if (!hasEaten(newSnake.getHead())) {
                snake = newSnake;
                continue;
            }
            if (hasEaten(newSnake.getHead())) {
                foodBlocks.remove(newSnake.getHead());
                newSnake.eatFoodBlock(snake.getTail());
                foodBlocks.add(generateFoodBlock());
                snake = newSnake;
            }
        }
    }


    private Point generateFoodBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        Point block;
        do {
            int xCoord = rnd.nextInt(width);
            int yCoord = rnd.nextInt(height);
            block = new Point(xCoord, yCoord);
        }
        while (foodBlocks.contains(block) || snake.getPoints().contains(block));
        return block;
    }

    private boolean allPointsOnBoard(List<com.snake.model.Point> newPoints) {
        return newPoints.stream().noneMatch(point -> point.getX() < 0 || point.getY() < 0 || point.getY() > height - 1 || point.getX() > width - 1);
    }

    private boolean hasEaten(Point newHead) {
        if (foodBlocks.contains(newHead)) return true;
        return false;
    }

    public String getStringState() {
        StringBuilder field = new StringBuilder();
        char[][] arrayPrint = new char[height][width];
        for (int i = 0; i < height; i++)
            Arrays.fill(arrayPrint[i], ' ');
        for (Point point : foodBlocks) {
            arrayPrint[point.getY()][point.getX()] = '#';
        }
        for (Point point : snake.getPoints()) {
            arrayPrint[point.getY()][point.getX()] = '*';
        }
        if (snake.getHeadDirection() == Snake.Direction.LEFT)
            arrayPrint[snake.getHead().getY()][snake.getHead().getX()] = '<';
        if (snake.getHeadDirection() == Snake.Direction.UP)
            arrayPrint[snake.getHead().getY()][snake.getHead().getX()] = '0';
        if (snake.getHeadDirection() == Snake.Direction.RIGHT)
            arrayPrint[snake.getHead().getY()][snake.getHead().getX()] = '>';
        if (snake.getHeadDirection() == Snake.Direction.DOWN)
            arrayPrint[snake.getHead().getY()][snake.getHead().getX()] = '0';

        for (int i = 0; i < height; i++) {
            field.append('.');
            for (int j = 0; j < width; j++) {
                field.append(arrayPrint[i][j]);
                field.append('.');
            }
            field.append('\n');
        }
        return field.toString();
    }


}


