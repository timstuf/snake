package com.snake.model;

public class MoveEvent {
    private final MoveEventType type;

    public MoveEvent(MoveEventType type) {
        this.type = type;
    }

    public MoveEventType getType() {
        return type;
    }

    public enum MoveEventType{
        RIGHT,
        LEFT,
        FORWARD
    }
}
