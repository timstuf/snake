package com.snake.model;

import java.util.ArrayList;
import java.util.List;

import static com.snake.model.MoveEvent.MoveEventType.*;

public class Snake {
    private List<Point> points;
    private Point head;
    private Direction headDirection;

    public Snake(List<Point> points, Direction headDirection) {
        this.points = points;
        this.head = points.get(0);
        this.headDirection = headDirection;
    }

    public Snake move(MoveEvent.MoveEventType eventType) {
        if (eventType == LEFT) {
            List<Point> newPoints = new ArrayList<>();
            newPoints.addAll(points);
            newPoints.add(0, moveLeft());
            newPoints.remove(newPoints.size() - 1);
            return new Snake(newPoints, getNewDirection(-1));
        }
        if (eventType == RIGHT) {
            List<Point> newPoints = new ArrayList<>();
            newPoints.addAll(points);
            newPoints.add(0, moveRight());
            newPoints.remove(newPoints.size() - 1);
            return new Snake(newPoints, getNewDirection(+1));
        }
        if (eventType == FORWARD) {
            List<Point> newPoints = new ArrayList<>();
            newPoints.addAll(points);
            newPoints.add(0, moveForward());
            newPoints.remove(newPoints.size() - 1);
            return new Snake(newPoints, getNewDirection(0));
        }
        throw new IllegalArgumentException("Invalid move state");
    }

    public Direction getHeadDirection() {
        return headDirection;
    }

    private Point moveLeft() {
        if (headDirection == Direction.UP) {
            return new Point(head.getX() - 1, head.getY());
        }
        if (headDirection == Direction.DOWN) {
            return new Point(head.getX() + 1, head.getY());
        }
        if (headDirection == Direction.LEFT) {
            return new Point(head.getX(), head.getY() + 1);
        }
        if (headDirection == Direction.RIGHT) {
            return new Point(head.getX(), head.getY() - 1);
        }
        throw new IllegalArgumentException("Invalid moveLeft state");
    }

    private Point moveRight() {
        if (headDirection == Direction.UP) {
            return new Point(head.getX() + 1, head.getY());
        }
        if (headDirection == Direction.DOWN) {
            return new Point(head.getX() - 1, head.getY());
        }
        if (headDirection == Direction.LEFT) {
            return new Point(head.getX(), head.getY() - 1);
        }
        if (headDirection == Direction.RIGHT) {
            return new Point(head.getX(), head.getY() + 1);
        }
        throw new IllegalArgumentException("Invalid moveRight state");
    }

    private Point moveForward() {
        if (headDirection == Direction.UP) {
            return new Point(head.getX(), head.getY() - 1);
        }
        if (headDirection == Direction.DOWN) {
            return new Point(head.getX(), head.getY() + 1);
        }
        if (headDirection == Direction.LEFT) {
            return new Point(head.getX() - 1, head.getY());
        }
        if (headDirection == Direction.RIGHT) {
            return new Point(head.getX() + 1, head.getY());
        }
        throw new IllegalArgumentException("Invalid moveForward state");
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getHead() {
        return head;
    }

    private Direction getNewDirection(int direction) {
        if (direction == 0) return headDirection;
        if (direction == -1) {
            if (headDirection == Direction.LEFT) return Direction.DOWN;
            else if (headDirection == Direction.DOWN) return Direction.RIGHT;
            else if (headDirection == Direction.RIGHT) return Direction.UP;
            else if (headDirection == Direction.UP) return Direction.LEFT;
            else throw new IllegalStateException("Wrong state of headDirection");
        }
        if (direction == 1) {
            if (headDirection == Direction.LEFT) return Direction.UP;
            else if (headDirection == Direction.UP) return Direction.RIGHT;
            else if (headDirection == Direction.RIGHT) return Direction.DOWN;
            else if (headDirection == Direction.DOWN) return Direction.LEFT;
            else throw new IllegalStateException("Wrong state of headDirection");
        }
        throw new IllegalArgumentException("Wrong number of direction");
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
