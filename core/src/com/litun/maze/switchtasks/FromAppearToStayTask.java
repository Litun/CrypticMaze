package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.Textures;
import com.litun.maze.components.BridgeComponent;
import com.litun.maze.components.PositionComponent;
import com.litun.maze.components.TextureComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class FromAppearToStayTask extends SwitchTask {
    private final PositionComponent position;
    private float distance, speed;
    private boolean firstPart = true;

    public FromAppearToStayTask(Entity bridge) {
        super(bridge);
        position = bridge.getComponent(PositionComponent.class);
        distance = position.scale.x * 0.4f / 2;
        speed = distance * 4;
    }

    @Override
    public void update(float deltaTime) {
        if (!finished) {
            if (firstPart) {
                position.scale.sub(0, speed * deltaTime);
                if (position.scale.y < 0) {
                    position.scale.y = 0;
                    bridge.getComponent(TextureComponent.class).region = Textures.tile;
                    firstPart = false;
                }
            } else {
                position.scale.add(0, speed * 2 * deltaTime);
                if (position.scale.y > distance * 2) {
                    position.scale.y = distance * 2;
                    finished = true;
                    bridge.getComponent(BridgeComponent.class).state = BridgeComponent.BridgeState.STAY;
                }
            }
        }
    }
}
