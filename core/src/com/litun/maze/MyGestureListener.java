package com.litun.maze;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.litun.maze.systems.InputSystem;

/**
 * Created by user on 30.03.2015.
 */
public class MyGestureListener implements GestureListener {

    Engine engine;

    public MyGestureListener(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        InputSystem input = engine.getSystem(InputSystem.class);
        if (input!=null) {
            if (velocityY < velocityX)
                if (velocityY > -velocityX)
                    input.swipe(InputSystem.SwipeKey.RIGHT);
                else input.swipe(InputSystem.SwipeKey.UP);
            else if (velocityY > -velocityX)
                input.swipe(InputSystem.SwipeKey.DOWN);
            else input.swipe(InputSystem.SwipeKey.LEFT);
            return true;
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
