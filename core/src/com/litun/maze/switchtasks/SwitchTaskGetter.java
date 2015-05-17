package com.litun.maze.switchtasks;

import com.badlogic.ashley.core.Entity;
import com.litun.maze.components.BridgeComponent;

/**
 * Created by Litun on 16.05.2015.
 */
public class SwitchTaskGetter {
    public static SwitchTask getTask(Entity bridge, boolean nextDimensionExistence) {
        SwitchTask task = null;
        switch (bridge.getComponent(BridgeComponent.class).state) {
            case STAY:
                if (!nextDimensionExistence)
                    task = new FromStayToDisappearTask(bridge);
                break;
            case DISAPPEAR:
                if (!nextDimensionExistence)
                    task = new FromDisappearToNothingTask(bridge);
                else
                    task = new FromDisappearToAppearTask(bridge);
                break;
            case NOTHING:
                if (nextDimensionExistence)
                    task = new FromNothingToAppearTask(bridge);
                break;
            case APPEAR:
                if (!nextDimensionExistence)
                    task = new FromAppearToDisappearTask(bridge);
                else task = new FromAppearToStayTask(bridge);
        }
        return task;
    }

    public static SwitchTask getInitTask(Entity bridge, boolean currentDimentionExistence, boolean nextDimentionExistence) {
        SwitchTask task = null;
        if (currentDimentionExistence)
            if (nextDimentionExistence)
                task = new ToStayTask(bridge);
            else task = new ToDisappearTask(bridge);
        else if (nextDimentionExistence)
            task = new FromNothingToAppearTask(bridge);
        return task;
    }
}
