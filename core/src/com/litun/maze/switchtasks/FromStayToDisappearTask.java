package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.PositionComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class FromStayToDisappearTask extends SwitchTask {

    private PositionComponent position;
    private float step;

    public FromStayToDisappearTask(Entity bridge) {
        super(bridge);
        position = bridge.getComponent(PositionComponent.class);
        step = (LARGE_SCALE - SMALL_SCALE) / 2;
    }

    @Override
    public void update(float deltaTime) {
        if (!finished) {
            position.scale.sub(0, step*deltaTime);
            if (position.scale.y < SMALL_SCALE) {
                position.scale.y = SMALL_SCALE;
                finished = true;
            }
        }
    }
}
