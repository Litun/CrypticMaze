package com.litun.maze.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Litun on 16.05.2015.
 */
public class BridgeComponent extends Component {
    public BridgeState state;
    public int i, j;
    public BridgeDirection direction;

    public BridgeComponent(int i, int j, BridgeDirection direction) {
        this.i = i;
        this.j = j;
        this.direction = direction;
    }

   public enum BridgeState {
        NOTHING,
        APPEAR,
        STAY,
        DISAPPEAR
    }

    public enum BridgeDirection {
        RIGHT,
        DOWN
    }
}




