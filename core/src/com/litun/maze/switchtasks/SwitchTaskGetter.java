package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.BridgeComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class SwitchTaskGetter {
    public static SwitchTask getTask(Entity bridge, boolean nextDimentionExistence) {
        SwitchTask task = null;
        switch (bridge.getComponent(BridgeComponent.class).state) {
            case STAY:
                if (!nextDimentionExistence)
                    task = new FromStayToDisappearTask(bridge);
                break;
            case DISAPPEAR:
                if (!nextDimentionExistence)
                    task = new FromDisappearToNothingTask(bridge);
                else
                    task = new FromDisappearToAppearTask(bridge);
                break;
            case NOTHING:
                if (nextDimentionExistence)
                    task = new FromNothingToAppearTask(bridge);
                break;
            case APPEAR:
                if (!nextDimentionExistence)
                    task = new FromAppearToDisappearTask(bridge);
                else task=new FromAppearToStayTask(bridge);
        }
        return task;
    }
}
