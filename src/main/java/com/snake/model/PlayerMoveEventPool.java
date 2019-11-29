package com.snake.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.snake.model.MoveEvent.MoveEventType.*;

public class PlayerMoveEventPool {
    public final Map<String, MoveEvent> pool;

    public PlayerMoveEventPool() {
        Map<String, MoveEvent> pool = new HashMap<>();
        pool.put("a", new MoveEvent(LEFT));
        pool.put("w", new MoveEvent(FORWARD));
        pool.put("d", new MoveEvent(RIGHT));
        this.pool = Collections.unmodifiableMap(pool);
    }
}
