package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.PositionComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class FromDisappearToNothingTask extends SwitchTask {
    private final PositionComponent position;
    private final float step;

    public FromDisappearToNothingTask(Entity bridge) {
        super(bridge);
        position = bridge.getComponent(PositionComponent.class);
        step = SMALL_SCALE / 2;
    }

    @Override
    public void update(float deltaTime) {
        if (!finished) {
            position.scale.sub(0, step*deltaTime);
            if (position.scale.y < 0) {
                position.scale.y = 0f;
                finished = true;
            }
        }
    }
}
