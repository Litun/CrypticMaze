package com.litun.maze.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

/**
 * Created by user on 10.04.2015.
 */
public class InputSystem extends EntitySystem {
    Engine engine;

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    public void swipe(SwipeKey key) {
        switch (key) {
            case LEFT:
                if (engine.getSystem(CharacterSystem.class).goLeft()) {
                    engine.getSystem(SwitchSystem.class).start();
                    engine.getSystem(MoveTilesSystem.class).start();
                }
                break;
            case RIGHT:
                if (engine.getSystem(CharacterSystem.class).goRight()) {
                    engine.getSystem(SwitchSystem.class).start();
                    engine.getSystem(MoveTilesSystem.class).start();
                }
                break;
            case UP:
                if (engine.getSystem(CharacterSystem.class).goUp()) {
                    engine.getSystem(SwitchSystem.class).start();
                    engine.getSystem(MoveTilesSystem.class).start();
                }
                break;
            case DOWN:
                if (engine.getSystem(CharacterSystem.class).goDown()) {
                    engine.getSystem(SwitchSystem.class).start();
                    engine.getSystem(MoveTilesSystem.class).start();
                }
                break;
        }
    }

    public enum SwipeKey {
        LEFT, UP, RIGHT, DOWN
    }
}


