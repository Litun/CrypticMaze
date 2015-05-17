package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.Textures;
import com.litun.maze.components.BridgeComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;

/**
 * Created by Litun on 17.05.2015.
 */
public class ToDisappearTask extends SwitchTask {
    private final PositionComponent position;
    private float distance, speed;

    public ToDisappearTask(Entity bridge) {
        super(bridge);
        position = bridge.getComponent(PositionComponent.class);
        distance = position.scale.x * 0.4f / 2;
        speed = distance * 2;
    }

    @Override
    public void update(float deltaTime) {
        if (!finished) {
            position.scale.add(0, speed * deltaTime);
            if (position.scale.y > distance) {
                position.scale.y = distance;
                finished = true;
                bridge.getComponent(BridgeComponent.class).state = BridgeComponent.BridgeState.DISAPPEAR;
            }
        }
    }
}