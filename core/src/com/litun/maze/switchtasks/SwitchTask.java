package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.TextureComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public abstract class SwitchTask {
    protected static final float LARGE_SCALE = 0.4f,
            SMALL_SCALE = 0.2f;
    protected Entity bridge;
    public boolean finished = false;

    public SwitchTask(Entity bridge) {
        this.bridge = bridge;
    }

    public abstract void update(float deltaTime);
    public void start() {
        finished = false;
    }
}
