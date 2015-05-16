package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.BridgeComponent;
import com.litun.maze.components.PositionComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class FromStayToDisappearTask extends SwitchTask {

    private PositionComponent position;
    private float distance, speed;

    public FromStayToDisappearTask(Entity bridge) {
        super(bridge);
        position = bridge.getComponent(PositionComponent.class);
        distance = position.scale.y / 2;
        speed = distance * 2;
    }

    @Override
    public void update(float deltaTime) {
        if (!finished) {
            position.scale.sub(0, speed * deltaTime);
            if (position.scale.y < distance) {
                position.scale.y = distance;
                finished = true;
                bridge.getComponent(BridgeComponent.class).state = BridgeComponent.BridgeState.DISAPPEAR;
            }
        }
    }
}
