package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;

/**
 * Created by Litun on 16.05.2015.
 */
public class FromAppearToStayTask extends SwitchTask {
    public FromAppearToStayTask(Entity bridge) {
        super(bridge);
    }

    @Override
    public void update(float deltaTime) {

    }
}
