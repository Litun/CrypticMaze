package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.BridgeComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class FromDisappearToNothingTask extends SwitchTask {
    private final PositionComponent position;
    private float distance, speed;

    public FromDisappearToNothingTask(Entity bridge) {
        super(bridge);
        position = bridge.getComponent(PositionComponent.class);
        distance = position.scale.y / 2;
        speed = distance*2;
    }

    @Override
    public void update(float deltaTime) {
        if (!finished) {
            position.scale.sub(0, speed * deltaTime);
            if (position.scale.y < 0) {
                bridge.getComponent(TextureComponent.class).region=null;
                finished = true;
                bridge.getComponent(BridgeComponent.class).state = BridgeComponent.BridgeState.NOTHING;
            }
        }
    }
}
